package pro.legend.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "NodeClient",url = "http://localhost:8082/")
public interface NodeClient {
    @GetMapping()
    Map get();
}
