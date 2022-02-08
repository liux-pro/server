package pro.liux.web.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.client.OssClient;
import pro.liux.web.service.BlogService;
import pro.liux.web.vo.oss.ListBucketResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("oss")
public class OssController {

//    @Autowired
//    S3Client s3Client;

    @Autowired
    BlogService blogService;

    @Autowired
    OssClient ossClient;

    /**
     * 上传图片接口
     *
     * @param filename
     * @param request
     * @return
     */
    @PutMapping(path = "{filename}", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @SneakyThrows
    void storeAsset(@PathVariable("filename") String filename, HttpServletRequest request) {
        InputStream body = request.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] b = new byte[10240];
        int count;
        while ((count = body.read(b)) != -1) {
            outStream.write(b, 0, count);// 写入数据
        }
        body.close();
        outStream.close();
        ossClient.put(filename, outStream.toByteArray());
    }

    @GetMapping(path = "{filename}")
    ResponseEntity<byte[]> get(@PathVariable("filename") String filename) {
        byte[] bytes = ossClient.get(filename);
        MultiValueMap<String, String> headers = new HttpHeaders();
        return new ResponseEntity<>(
                bytes,
                headers,
                HttpStatus.OK
        );
    }


    @GetMapping("")
    public ListBucketResult bbb() {
        ListBucketResult post = ossClient.list(100);
        System.out.println(post);
        return post;
    }

    @PostMapping("ossAuth")
    public void redirectOss(@RequestParam("url") String url, HttpServletResponse response) throws IOException {
//        String directLink = blogService.getOssDirectLink(url);
        String authedUrl = blogService.getAuthedUrl(url, 1000);
        System.out.println(authedUrl);
        response.sendRedirect(authedUrl);
    }
}
