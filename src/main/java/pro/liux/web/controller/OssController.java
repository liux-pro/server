package pro.liux.web.controller;

import feign.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.client.S3Client;
import pro.liux.web.service.BlogService;
import pro.liux.web.vo.s3.ListBucketResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("oss")
public class OssController {

    @Autowired
    S3Client s3Client;

    @Autowired
    BlogService blogService;

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
    Object storeAsset(@PathVariable("filename") String filename, HttpServletRequest request) {
        InputStream body = request.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] b = new byte[10240];
        int count;
        while ((count = body.read(b)) != -1) {
            outStream.write(b, 0, count);// 写入数据
        }
        body.close();
        outStream.close();
        Response put = s3Client.put(filename, outStream.toByteArray());
        return put.status();
    }

    @GetMapping(path = "{filename}")
    Response get(@PathVariable("filename") String filename) {
        Response map = s3Client.get(filename);
        System.out.println("map = " + map);
        return map;
    }


    @GetMapping("")
    public ListBucketResult bbb() {
        ListBucketResult post = s3Client.list("liux-pro", 100);
        System.out.println(post);
        return post;
    }

    @PostMapping("ossAuth")
    public void redirectOss(@RequestParam("url") String url, HttpServletResponse response) throws IOException {
//        String directLink = blogService.getOssDirectLink(url);
        String authedUrl = blogService.getAuthedUrl(url,1000);
        System.out.println(authedUrl);
        response.sendRedirect(authedUrl);
    }
}
