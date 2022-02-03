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
import pro.liux.web.utils.UUID;
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
    S3Client s3Client;

    @Autowired
    BlogService blogService;


    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file[]") MultipartFile file) throws IOException {
        String imgURL = blogService.uploadImg(UUID.getUUID() + file.getOriginalFilename(), file.getBytes());

        VditorImage vditorImage = VditorImage.
                builder()
                .succMap(Collections.singletonMap(file.getOriginalFilename(), imgURL))
                .build();
        Result result = Result.success(null);
        result.setData(vditorImage);
        return result;
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


}
