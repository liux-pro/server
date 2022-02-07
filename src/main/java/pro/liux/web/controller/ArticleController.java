package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.service.BlogService;
import pro.liux.web.service.TestService;
import pro.liux.web.utils.UUID;
import pro.liux.web.vo.Result;
import pro.liux.web.vo.VditorImage;
import pro.liux.web.vo.VditorImageConvert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    TestService testService;


    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    BlogService blogService;

    /**
     * vditor 上传文件
     *
     * @param file form-data 表单文件
     * @return vditor兼容的图片地址
     */
    @PostMapping("upload")
    public Result uploadFile(@RequestParam("file[]") MultipartFile file) throws IOException {
        String imgURL = blogService.uploadImg(UUID.getUUID() + "/" + file.getOriginalFilename(), file.getBytes());

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
                            .url(oldUrl)
                            .build();
            return Result.success(convert);
        }
        return Result.badRequest();
    }


    /**
     * vditor 上传文件
     *
     * @param article 文章
     */
    @PostMapping("article/{id}")
    public Result articleSave(@RequestBody Article article, @PathVariable("id") Long id) {
        article.setId(id);
        LocalDateTime now = LocalDateTime.now();
        article.setGmtCreate(now);
        article.setGmtModified(now);
        int insert = articleMapper.insert(article);
        System.out.println(insert);
        Result result = Result.success(article);
        return result;
    }
}
