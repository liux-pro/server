package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleExample;
import pro.liux.web.model.ArticleVersion;
import pro.liux.web.service.ArticleService;
import pro.liux.web.vo.Page;
import pro.liux.web.vo.Result;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping("view")
public class ArticleViewController {
    @Autowired
    ArticleService articleService;


    @GetMapping("article")
    public Result<Page<Article>> articleList() {
        List<Article> articles = articleService.selectByExample(null);
        Page<Article> articlePage = new Page<>(articles);
        articlePage.setTotal(100);
        return Result.success(articlePage);
    }
}
