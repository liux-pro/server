package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleDetail;
import pro.liux.web.service.ArticleService;
import pro.liux.web.service.ArticleViewService;
import pro.liux.web.vo.Page;
import pro.liux.web.vo.Result;



@RestController()
@RequestMapping("view")
public class ArticleViewController {
    @Autowired
    ArticleViewService articleViewService;

    @GetMapping("article")
    public Result<Page<Article>> getArticlePage(@RequestParam(defaultValue = "20") Long pageSize,
                                             @RequestParam(defaultValue = "1") Long pageNum) {
        Page<Article> page = articleViewService.getPage(pageSize, pageNum);
        return Result.success(page);
    }
    @GetMapping("article/{articleId}")
    public Result<ArticleDetail> getArticleDetail(@PathVariable("articleId")Long articleId) {
        ArticleDetail detail = articleViewService.getPublishedArticleDetail(articleId);
        return Result.success(detail);
    }
}
