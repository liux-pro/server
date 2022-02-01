package pro.liux.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pro.liux.web.utils.AWSSignatureVersion4;

@ConfigurationProperties(prefix = "oss.s3")
@Getter
@Setter
public class S3FeignConfiguration extends AWSSignatureVersion4 {
    private String region;
    private String service;
    private String accessKey;
    private String secretKey;
    public S3FeignConfiguration(){
        System.out.println(this.region);
    }
}
