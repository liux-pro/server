package pro.liux.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pro.liux.web.utils.AWSSignatureVersion4;

@ConfigurationProperties(prefix ="oss.s3")
public class S3FeignConfiguration extends AWSSignatureVersion4 {
    private static String region;
    private static String service;
    private static String accessKey;
    private static String secretKey;

    public S3FeignConfiguration() {
        super(region, service, accessKey, secretKey);
    }
}
