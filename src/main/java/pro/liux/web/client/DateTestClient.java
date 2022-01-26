package pro.liux.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DateTestClient",url = "https://jsonplaceholder.typicode.com/")
public interface DateTestClient {
    @GetMapping("posts/1")
    Object getPost();
}
