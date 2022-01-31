package pro.liux.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pro.liux.web.utils.AWSSignatureVersion4;

import java.util.Map;

@FeignClient(name = "S3Client", configuration = AWSSignatureVersion4.class, url = "http://1.1.1.88:9000")
public interface S3Client {
    @GetMapping("/")
    Map info();

    @GetMapping("/{bucket}")
    Map list(@PathVariable("bucket") String bucket);

    @PutMapping("/test/{filename}")
    Map put(@PathVariable("filename") String filename, byte[] bytes);
}
