package pro.liux.web.client;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import pro.liux.web.utils.AWSSignatureVersion4;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
public class BaseClient {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:9000/liux-pro";

    {
//        url = "http://www.httpbin.org/get";
        restTemplate.setInterceptors(List.of(new Hook()));
    }

    public Boolean uploadFile(byte[] bytes) {
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);


        return true;
    }

    public static class Hook implements ClientHttpRequestInterceptor {

        @NotNull
        @Override
        public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, @NotNull ClientHttpRequestExecution execution) throws IOException {
            AWSSignatureVersion4.WebEntry webEntry = new AWSSignatureVersion4.WebEntry(
                    request.getHeaders(),
                    request.getMethodValue(),
                    request.getURI().getAuthority(),
                    request.getURI().getRawQuery(),
                    request.getURI().getPath(),
                    body
            );

            Map<String, List<String>> calc = new AWSSignatureVersion4().calc(webEntry);
            System.out.println("calc = " + calc);
            HttpHeaders headers = request.getHeaders();
            calc.forEach(headers::addAll);
            return execution.execute(request, body);
        }
    }
}
