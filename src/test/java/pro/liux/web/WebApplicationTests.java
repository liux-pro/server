package pro.liux.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
class WebApplicationTests {

    @Test
    void contextLoads() {

    }


    @Test
    public void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response
                = restTemplate.getForEntity("https://httpbin.org/json", Map.class);
        System.out.println(response.getBody());
    }
}
