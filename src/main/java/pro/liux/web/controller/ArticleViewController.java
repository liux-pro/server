package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.service.ArticleService;
import pro.liux.web.vo.Page;
import pro.liux.web.vo.Result;

import java.util.Optional;

@RestController()
@RequestMapping("view")
public class ArticleViewController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;

    @GetMapping("article")
    public Result<Page<Article>> articleList() {
//        List<Article> articles = articleService.selectByExample(null);
//        Page<Article> articlePage = new Page<>(articles);
//        articlePage.setTotal(100);
        return Result.success(null);
    }

    @GetMapping("test")
    public Result<String> lalal() {
        Optional<Article> article = articleMapper.selectByPrimaryKey(60L);
        return Result.success(article.get().getMainContent().toString());
    }
}
