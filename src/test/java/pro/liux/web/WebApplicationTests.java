package pro.liux.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pro.liux.web.client.S3Client;

import java.util.Map;

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


    @Test
    public void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response
                = restTemplate.getForEntity("https://httpbin.org/json", Map.class);
        System.out.println(response.getBody());
    }
}
