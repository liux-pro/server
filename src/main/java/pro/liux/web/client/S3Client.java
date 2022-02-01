package pro.liux.web.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pro.liux.web.config.S3FeignConfiguration;

import java.util.Map;

@FeignClient(name = "S3Client", configuration = S3FeignConfiguration.class, url = "https://s3-cn-north-1.qiniucs.com")
public interface S3Client {
    @GetMapping("/")
    Map info();

    @GetMapping("/{bucket}")
    Map list(@PathVariable("bucket") String bucket);

    @PutMapping("/liux-pro/{filename}")
    Map put(@PathVariable("filename") String filename, byte[] bytes);
    @GetMapping("/liux-pro/{filename}")
    Response get(@PathVariable("filename") String filename);
}
