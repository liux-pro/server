package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.service.FileService;
import pro.liux.web.service.TestService;
import pro.liux.web.vo.Result;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    TestService testService;


    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    FileService fileService;

    /**
     * vditor 上传文章
     *
     * @param article 文章
     */
    @PostMapping("article/{id}")
    public Result articleSave(@RequestBody @Validated Article article, @PathVariable("id") Long id) {
        article.setId(id);
        LocalDateTime now = LocalDateTime.now();
        article.setGmtCreate(now);
        article.setGmtModified(now);
        int insert = articleMapper.insert(article);
        System.out.println(insert);
        Result result = Result.success(article);
        return result;
    }

    @GetMapping("article/{id}")
    public Result articleGet(@PathVariable("id") Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        Result result = Result.success(article);
        return result;
    }
}
