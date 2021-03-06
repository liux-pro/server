/*
 * Copyright 2012-2022 The Feign Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.liux.web.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.Comparators;
import pro.liux.web.config.property.OSS;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


// origin code from https://github.com/OpenFeign/feign/blob/master/sax/src/test/java/feign/sax/examples/AWSSignatureVersion4.java
// http://docs.aws.amazon.com/general/latest/gr/signature-version-4.html
@Getter
@Setter
@Configuration
public class AWSSignatureVersion4 {
    private static final String EMPTY_STRING_HASH =
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    private static final SimpleDateFormat iso8601 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    static {
        //必须用GMT
        iso8601.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    private String region;
    private String service;
    private String accessKey;
    private String secretKey;
    private String endpoint;

    public AWSSignatureVersion4(@Autowired OSS oss) {
        this.region = oss.getRegion();
        this.service = oss.getService();
        this.accessKey = oss.getAccessKey();
        this.secretKey = oss.getSecretKey();
        this.endpoint = oss.getEndpoint();
        //目前native不不能用
        //BeanUtils.copyProperties(oss, this);
    }

    private static String canonicalString(WebEntry input, Map<String, List<String>> headers, String signedHeaders, String hash) {
        StringBuilder canonicalRequest = new StringBuilder();
        // 请求方式（GET POST） + '\n' +
        canonicalRequest.append(input.getMethod().toUpperCase()).append('\n');
        // 域名后面的部分，不算get参数，以/开头
        // CanonicalURI + '\n' +
        canonicalRequest.append(input.getUriPath()).append('\n');
        //
        // CanonicalQueryString + '\n' +
        if (StringUtils.hasText(input.getQueryString())) {
            //去除问号
            canonicalRequest.append(input.getQueryString());
        }
        canonicalRequest.append('\n');


        //所有header
        String canonicalHeaders = headers.entrySet().stream()
                //key转成小写
                .collect(Collectors.toMap(key -> key.getKey().toLowerCase()
                        , Map.Entry::getValue)).entrySet().stream()
                //以key排序
                .sorted(Map.Entry.comparingByKey())
                //拼接 key:value1,value2\n
                .map(entry -> String.join(":", entry.getKey(), String.join(",", entry.getValue())))
                //拼接的最后一个也有\n结尾
                .collect(Collectors.joining("\n", "", "\n"));
        // CanonicalHeaders + '\n' +
        canonicalRequest.append(canonicalHeaders).append('\n');


        canonicalRequest.append(signedHeaders).append('\n');
        // HexEncode(Hash(Payload))
        canonicalRequest.append(hash);

        return canonicalRequest.toString();
    }

    private static String toSign(String timestamp, String credentialScope, String canonicalRequest) {
        StringBuilder toSign = new StringBuilder();
        // Algorithm + '\n' +
        toSign.append("AWS4-HMAC-SHA256").append('\n');
        // RequestDate + '\n' +
        toSign.append(timestamp).append('\n');
        // CredentialScope + '\n' +
        toSign.append(credentialScope).append('\n');
        // HexEncode(Hash(CanonicalRequest))
        toSign.append(EncryptUtils.hex(EncryptUtils.sha256(canonicalRequest)));
        return toSign.toString();
    }

    /**
     * 计算签名
     *
     * @param input 传进来header和body
     * @return 计算完成的header，把请求header清空并且替换为这个map
     */
    public Map<String, List<String>> calc(WebEntry input) {
        // 可能传进来的是只读的map
        Map<String, List<String>> headers = new HashMap<>(input.headers);

        String timestamp;
        synchronized (iso8601) {
            timestamp = iso8601.format(new Date());
        }

        String credentialScope =
                String.format("%s/%s/%s/%s", timestamp.substring(0, 8), region, service, "aws4_request");


        byte[] body = input.getBody();
        String hash;
        if (body != null) {
            hash = EncryptUtils.hex(EncryptUtils.sha256(body));
        } else {
            hash = EMPTY_STRING_HASH;
        }
        headers.put("x-amz-content-sha256", List.of(hash));
        headers.put("Host", List.of(input.getHost()));
        headers.put("x-amz-date", List.of(timestamp));

        //所有key小写排序，分号分割，拼在一起
        String signedHeaders = headers.keySet().stream().sorted(Comparators.comparable())
                .map(String::toLowerCase).collect(Collectors.joining(";"));

        String canonicalString = canonicalString(input, headers, signedHeaders, hash);
        String toSign = toSign(timestamp, credentialScope, canonicalString);

        byte[] signatureKey = signatureKey(secretKey, timestamp);
        String signature = EncryptUtils.hex(EncryptUtils.hmacSHA256(toSign, signatureKey));
        String authorization = String.format("AWS4-HMAC-SHA256 Credential=%s/%s,SignedHeaders=%s,Signature=%s"
                , accessKey, credentialScope, signedHeaders, signature);
        headers.put("Authorization", List.of(authorization));
        return headers;
    }

    byte[] signatureKey(String secretKey, String timestamp) {
        byte[] kSecret = ("AWS4" + secretKey).getBytes(StandardCharsets.UTF_8);
        byte[] kDate = EncryptUtils.hmacSHA256(timestamp.substring(0, 8), kSecret);
        byte[] kRegion = EncryptUtils.hmacSHA256(region, kDate);
        byte[] kService = EncryptUtils.hmacSHA256(service, kRegion);
        byte[] kSigning = EncryptUtils.hmacSHA256("aws4_request", kService);
        return kSigning;
    }

    /**
     * 封装Headers和请求
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class WebEntry {
        private Map<String, List<String>> headers;
        private String method;
        private String host;
        private String queryString;
        private String uriPath;
        private byte[] body;
    }
}