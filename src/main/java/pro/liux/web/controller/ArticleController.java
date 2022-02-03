package pro.liux.web.controller;

import feign.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.client.S3Client;
import pro.liux.web.service.BlogService;
import pro.liux.web.service.TestService;
import pro.liux.web.vo.*;
import pro.liux.web.vo.s3.ListBucketResult;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    TestService testService;

    @Autowired
    DateTestClient dateTestClient;

    @Autowired
    S3Client s3Client;

    @Autowired
    BlogService blogService;


    @PostMapping("markdown")
    public String getArticle() {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        return "# HelloWord";
    }


    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file[]") MultipartFile file) throws IOException {
        String imgURL = blogService.uploadImg(file.getOriginalFilename(), file.getBytes());


        VditorImage vditorImage = VditorImage.
                builder()
                .succMap(Collections.singletonMap(file.getOriginalFilename(), imgURL))
                .build();
        Result result = Result.success(null);
        result.setData(vditorImage);
        return result;
    }

    @PostMapping("aaa")
    public VditorImage aaa() {
        Object post = dateTestClient.getPost();

        VditorImage build = VditorImage.builder().errFiles(Arrays.asList("1", "2"))
                .succMap(new HashMap<String, String>() {{
                    put("1", "2");
                }}).build();

        return build;
    }

    @GetMapping("s3/list")
    public ListBucketResult bbb() {
        ListBucketResult post = s3Client.list("liux-pro", 100);
        System.out.println(post);
        return post;
    }

    /**
     * vditor 图片链接转换，下载原链接图片并转存返回新url
     *
     * @param source {”url“：origin-url}
     */
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

    /**
     * 上传图片接口
     *
     * @param filename
     * @param request
     * @return
     */
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
    Response get(@PathVariable("filename") String filename) {
        Response map = s3Client.get(filename);
        System.out.println("map = " + map);
        return map;
    }
}
