package pro.liux.web.config.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "liux-pro")
public class LiuxPro {
    @Autowired
    private OSS oss;
    /**
     * 域名
     */
    private String host;
}
