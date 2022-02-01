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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.Comparators;
import pro.liux.web.config.SpringNativeFeignConfiguration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static feign.Util.UTF_8;

// origin code from https://github.com/OpenFeign/feign/blob/master/sax/src/test/java/feign/sax/examples/AWSSignatureVersion4.java
// http://docs.aws.amazon.com/general/latest/gr/signature-version-4.html
@Getter
@Setter
public class AWSSignatureVersion4 implements RequestInterceptor {
    private static final String EMPTY_STRING_HASH =
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    private static final SimpleDateFormat iso8601 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    private String region;
    private String service;
    private String accessKey;
    private String secretKey;
    private String endpoint;

    public AWSSignatureVersion4() {
        this.region = SpringNativeFeignConfiguration.region;
        this.service = SpringNativeFeignConfiguration.service;
        this.accessKey = SpringNativeFeignConfiguration.accessKey;
        this.secretKey = SpringNativeFeignConfiguration.secretKey;
        this.endpoint = SpringNativeFeignConfiguration.endpoint;
    }


    static {
        //必须用GMT
        iso8601.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    static byte[] hmacSHA256(String data, byte[] key) {
        try {
            String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(data.getBytes(UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String canonicalString(RequestTemplate input, String signedHeaders, String hash) {
        StringBuilder canonicalRequest = new StringBuilder();
        // 请求方式（GET POST） + '\n' +
        canonicalRequest.append(input.method()).append('\n');
        // 域名后面的部分，不算get参数，以/开头
        // CanonicalURI + '\n' +
        canonicalRequest.append(URI.create(input.url()).getPath()).append('\n');
        //
        // CanonicalQueryString + '\n' +
        if (StringUtils.hasText(input.queryLine())) {
            //去除问号
            canonicalRequest.append(input.queryLine().substring(1));
        }
        canonicalRequest.append('\n');


        //所有header
        String canonicalHeaders = input.headers().entrySet().stream()
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
        toSign.append(hex(sha256(canonicalRequest)));
        return toSign.toString();
    }


    private static String hex(byte[] data) {
        StringBuilder result = new StringBuilder(data.length * 2);
        for (byte b : data) {
            result.append(String.format("%02x", b & 0xff));
        }
        return result.toString();
    }

    static byte[] sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data.getBytes(UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static byte[] sha256(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public void apply(RequestTemplate input) {
        Target<?> target = input.feignTarget();

        URL url = new URL(target.url());
        String host = url.getAuthority();

        String timestamp;
        synchronized (iso8601) {
            timestamp = iso8601.format(new Date());
        }

        String credentialScope =
                String.format("%s/%s/%s/%s", timestamp.substring(0, 8), region, service, "aws4_request");


        byte[] body = input.body();
        String hash;
        if (body != null) {
            hash = hex(sha256(body));
        } else {
            hash = EMPTY_STRING_HASH;
        }
        input.header("x-amz-content-sha256", hash);
        input.header("Host", host);
        input.header("x-amz-date", timestamp);

        //所有key小写排序，分号分割，拼在一起
        String signedHeaders = input.headers().keySet().stream().sorted(Comparators.comparable())
                .map(String::toLowerCase).collect(Collectors.joining(";"));

        String canonicalString = canonicalString(input, signedHeaders, hash);
        String toSign = toSign(timestamp, credentialScope, canonicalString);

        byte[] signatureKey = signatureKey(secretKey, timestamp);
        String signature = hex(hmacSHA256(toSign, signatureKey));
        String authorization = String.format("AWS4-HMAC-SHA256 Credential=%s/%s,SignedHeaders=%s,Signature=%s"
                , accessKey, credentialScope, signedHeaders, signature);
        input.header("Authorization", authorization);
    }

    byte[] signatureKey(String secretKey, String timestamp) {
        byte[] kSecret = ("AWS4" + secretKey).getBytes(UTF_8);
        byte[] kDate = hmacSHA256(timestamp.substring(0, 8), kSecret);
        byte[] kRegion = hmacSHA256(region, kDate);
        byte[] kService = hmacSHA256(service, kRegion);
        byte[] kSigning = hmacSHA256("aws4_request", kService);
        return kSigning;
    }
}