package pro.liux.web.controller;

import feign.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.client.S3Client;
import pro.liux.web.service.TestService;
import pro.liux.web.vo.Result;
import pro.liux.web.vo.VditorImage;
import pro.liux.web.vo.VditorImageConvert;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    TestService testService;

    @Autowired
    DateTestClient dateTestClient;

    @Autowired
    S3Client s3Client;

    @Value("${oss.s3.access-key}")
    private String inject;


    @PostMapping("markdown")
    public String getArticle() {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        return "# HelloWord";
    }

    private String fileLocation = "";

    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file[]") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        VditorImage vditorImage = VditorImage.
                builder()
                .succMap(Collections.singletonMap(file.getOriginalFilename(), "https://s3.bmp.ovh/imgs/2022/01/de5950621639e899.jpg"))
                .build();
        Result result = Result.success(null);
        result.setData(vditorImage);
        return result;
    }

    @PostMapping("aaa")
    public Object aaa() {
        Object post = dateTestClient.getPost();
        return post;
    }

    @GetMapping("s3/list")
    public Object bbb() {
        Map post  = s3Client.list("liux-pro");
        return post;
    }

    @PostMapping("imgUrlConvert")
    public Result uploadFile(@RequestBody Map<String, String> source) {
        String oldUrl = source.get("url");
        if (StringUtils.hasText(oldUrl)) {
            VditorImageConvert convert =
                    VditorImageConvert.builder()
                            .originalURL(oldUrl)
                            .url("https://via.placeholder.com/150")
                            .build();
            return Result.success(convert);
        }
        return Result.badRequest();
    }


    @PutMapping(path = "s3/{filename}", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
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
        return s3Client.put(filename, outStream.toByteArray());
    }
    @GetMapping(path = "s3/{filename}")
    Object get(@PathVariable("filename") String filename) {
       Response map = s3Client.get(filename);
        System.out.println("map = " + map);
        return inject;
    }
}
