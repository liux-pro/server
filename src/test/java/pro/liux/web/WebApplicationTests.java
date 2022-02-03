package pro.liux.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.liux.web.client.S3Client;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    S3Client s3Client;

    @Test
    void contextLoads() {

    }

    @Test
    public void s3test() {
        Object info = s3Client.info();
        System.out.println(info);
    }

}
