package pro.liux.web.client;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import pro.liux.web.config.property.OSS;
import pro.liux.web.utils.AWSSignatureVersion4;
import pro.liux.web.utils.SpringContextUtil;
import pro.liux.web.vo.s3.ListBucketResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class OssClient extends BaseClient {
    private final String baseUrl;

    {
        restTemplate.setInterceptors(List.of(new AmazonOssAuth()));
    }

    public OssClient(OSS oss) {
        this.baseUrl = String.format("%s://%s/%s/%%s", oss.getProtocol(),
                oss.getEndpoint(), oss.getBucket());
        System.out.println(this.baseUrl);
    }

    public ListBucketResult list(Integer maxKeys) {
        return restTemplate.getForObject(getFileUrl("?max-keys=" + maxKeys), ListBucketResult.class);
    }

    @SneakyThrows
    public void put(String fileName, byte[] bytes) {
        restTemplate.put(getFileUrl(fileName), bytes);
    }

    @SneakyThrows
    public byte[] get(String fileName) {
        byte[] forObject = restTemplate.getForObject(getFileUrl(fileName), byte[].class);
        System.out.println("forObject = " + forObject.length);
        return forObject;
    }

    /**
     * 生成文件直链
     *
     * @param fileKey 文件名
     * @return url
     */
    public String getFileUrl(String fileKey) {
        return String.format(this.baseUrl, fileKey);
    }

    public static class AmazonOssAuth implements ClientHttpRequestInterceptor {
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

            Map<String, List<String>> calc = new AWSSignatureVersion4(SpringContextUtil.getBean(OSS.class)).calc(webEntry);
            request.getHeaders().clear();
            HttpHeaders headers = request.getHeaders();
            calc.forEach(headers::addAll);
            return execution.execute(request, body);
        }
    }
}
