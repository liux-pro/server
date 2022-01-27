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
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static feign.Util.UTF_8;

// origin code from https://github.com/OpenFeign/feign/blob/master/sax/src/test/java/feign/sax/examples/AWSSignatureVersion4.java
// http://docs.aws.amazon.com/general/latest/gr/signature-version-4.html
@Configuration
public class AWSSignatureVersion4 implements RequestInterceptor {
    private static final String EMPTY_STRING_HASH =
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    private static final SimpleDateFormat iso8601 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    static {
//        iso8601.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        iso8601.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    String region = "myregion";
    String service = "s3";
    String accessKey;
    String secretKey;

    public AWSSignatureVersion4() {
        this.accessKey = "test-user";
        this.secretKey = "asd123456";
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

    private static String canonicalString(RequestTemplate input, String host,String timestamp,String signedHeaders) {
        StringBuilder canonicalRequest = new StringBuilder();
        // HTTPRequestMethod + '\n' +
        canonicalRequest.append(input.method()).append('\n');

        // CanonicalURI + '\n' +
        canonicalRequest.append(URI.create(input.url()).getPath()).append('\n');

        // CanonicalQueryString + '\n' +
        if (StringUtils.hasText(input.queryLine())) {
            //去除问号
            canonicalRequest.append(input.queryLine().substring(1));
        }
        canonicalRequest.append('\n');

        // CanonicalHeaders + '\n' +
        canonicalRequest.append("host:").append(host).append('\n');


        // x-amz-date + '\n' +
        canonicalRequest.append("x-amz-date:").append(timestamp).append('\n');

        canonicalRequest.append('\n');
        canonicalRequest.append(signedHeaders).append('\n');
        // HexEncode(Hash(Payload))
        byte[] data = input.body();
        String bodyText = (data != null) ? new String(data, input.requestCharset()) : null;
        if (bodyText != null) {
            canonicalRequest.append(hex(sha256(bodyText)));
        } else {
            canonicalRequest.append(EMPTY_STRING_HASH);
        }
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

    @Override
    @SneakyThrows
    public void apply(RequestTemplate input) {
        if (!input.headers().isEmpty()) {
            throw new UnsupportedOperationException("headers not supported");
        }
        if (input.body() != null) {
            throw new UnsupportedOperationException("body not supported");
        }
        Target<?> target = input.feignTarget();

        URL url = new URL(target.url());
        String host = url.getAuthority();

        String timestamp;
        synchronized (iso8601) {
//            timestamp = iso8601.format(new Date(1643248485000L));
            timestamp = iso8601.format(new Date());
        }

        String credentialScope =
                String.format("%s/%s/%s/%s", timestamp.substring(0, 8), region, service, "aws4_request");

        String signedHeaders = "host;x-amz-date";
//    input.header("X-Amz-Algorithm", "AWS4-HMAC-SHA256");
//    input.header("X-Amz-Credential", accessKey + "/" + credentialScope);
//    input.header("X-Amz-SignedHeaders", "host");
        input.header("Host", host);
        input.header("x-amz-date", timestamp);

        String canonicalString = canonicalString(input, host,timestamp,signedHeaders);
        String toSign = toSign(timestamp, credentialScope, canonicalString);

        byte[] signatureKey = signatureKey(secretKey, timestamp);
        String signature = hex(hmacSHA256(toSign, signatureKey));
        String authorization = String.format("AWS4-HMAC-SHA256 Credential=%s/%s,SignedHeaders=%s, Signature=%s"
                                                                ,accessKey, credentialScope, signedHeaders, signature);
        input.header("Authorization",authorization);
//    input.header("X-Amz-Signature", signature);

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