package pro.liux.web.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "oss.s3")
@Configuration
@Data
public class S3Property {
    private String region;
    private String service;
    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String bucket;
    private String cdnHost;
    /**
     * https or http
     */
    private String protocol;
}
