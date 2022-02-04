package pro.liux.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import pro.liux.web.client.BaseClient;
import pro.liux.web.utils.SpringContextUtil;

@SpringBootApplication
@ConfigurationPropertiesScan
@Import(SpringContextUtil.class)
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        BaseClient baseClient = new BaseClient();
        Boolean aBoolean = baseClient.uploadFile(new byte[1]);
        System.out.println(aBoolean);
    }

}
