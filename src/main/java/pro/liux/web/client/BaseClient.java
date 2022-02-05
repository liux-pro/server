package pro.liux.web.client;

import lombok.Data;
import org.springframework.web.client.RestTemplate;


@Data
public class BaseClient {
    protected RestTemplate restTemplate = new RestTemplate();
}
