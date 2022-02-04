package pro.liux.web.client;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
public class BaseClient {
    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://www.httpbin.org/post?abc=xyz";
    {
        restTemplate.setInterceptors(List.of(new Hook()));
    }

    public Boolean uploadFile(byte[] bytes) {
        String s = restTemplate.postForObject(url, "bytes".getBytes(StandardCharsets.UTF_8), String.class);
        System.out.println(s);


        return true;
    }

    public static class Hook implements ClientHttpRequestInterceptor {

        @NotNull
        @Override
        public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, @NotNull ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders();
            System.out.println(request.getURI().getQuery());
            return execution.execute(request, body);
        }
    }
}
