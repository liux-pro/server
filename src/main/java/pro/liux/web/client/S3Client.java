package pro.liux.web.client;

import feign.Headers;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.liux.web.utils.AWSSignatureVersion4;
import pro.liux.web.vo.s3.ListBucketResult;

import java.util.Map;

@FeignClient(name = "S3Client", configuration = AWSSignatureVersion4.class, url = "${oss.s3.endpoint}")
public interface S3Client {
    @GetMapping("/")
    Map info();

    @GetMapping("/{bucket}")
    ListBucketResult list(@PathVariable("bucket") String bucket, @RequestParam(value = "max-keys") Integer maxKeys);

    @PutMapping("/liux-pro/{filename}")
    Response put(@PathVariable("filename") String filename, byte[] bytes);

    @GetMapping("/liux-pro/{filename}")
    Response get(@PathVariable("filename") String filename);
}
