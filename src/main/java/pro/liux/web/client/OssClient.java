package pro.liux.web.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import pro.liux.web.config.property.OSS;
import pro.liux.web.utils.AWSSignatureVersion4;
import pro.liux.web.vo.oss.ListBucketResult;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class OssClient extends BaseClient {
    private final String baseUrl;
    @Autowired
    private AWSSignatureVersion4 awsSignatureVersion4;

    public OssClient(@Autowired OSS oss) {
        this.baseUrl = String.format("%s://%s/%s/%%s", oss.getProtocol(),
                oss.getEndpoint(), oss.getBucket());
        System.out.println(this.baseUrl);
    }

    @PostConstruct
    public void init() {
        restTemplate.setInterceptors(List.of(new AmazonOssAuth(awsSignatureVersion4)));
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
        AWSSignatureVersion4 aws;

        public AmazonOssAuth(AWSSignatureVersion4 aws) {
            this.aws = aws;
        }

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

            Map<String, List<String>> calc = aws.calc(webEntry);
            request.getHeaders().clear();
            HttpHeaders headers = request.getHeaders();
            calc.forEach(headers::addAll);
            ClientHttpResponse execute = execution.execute(request, body);
            execute.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE);
            return execute;
        }
    }
}
