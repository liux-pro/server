package pro.liux.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pro.liux.web.utils.AWSSignatureVersion4;

import java.util.Map;

@FeignClient(name = "S3Client",configuration = AWSSignatureVersion4.class , url = "http://1.1.1.88:9000")
public interface S3Client {
    @GetMapping("/")
    Map info();
}
