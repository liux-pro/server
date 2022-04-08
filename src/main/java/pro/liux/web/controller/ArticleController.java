package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.mapper.ArticleVersionMapper;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleVersion;
import pro.liux.web.model.ArticleVersionExample;
import pro.liux.web.service.ArticleService;
import pro.liux.web.service.ArticleVersionService;
import pro.liux.web.service.FileService;
import pro.liux.web.service.TestService;
import pro.liux.web.utils.IdWorker;
import pro.liux.web.vo.Result;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ArticleController {
    @Autowired
    IdWorker idWorker;
    @Autowired
    TestService testService;

    @Autowired
    ArticleVersionService articleVersionService;
    @Autowired
    ArticleService articleService;

    @Autowired
    FileService fileService;

    /**
     * vditor 上传文章到版本库
     *
     * @param article 文章
     */
    @PostMapping("article/{id}")
    public Result<String> articleSave(@RequestBody @Validated ArticleVersion article, @PathVariable("id") Long id) {
        article.setId(idWorker.nextId());
        article.setArticleId(id);
        LocalDateTime now = LocalDateTime.now();
        article.setGmtCreate(now);
        article.setGmtModified(now);
        int insert = articleVersionService.insert(article);
        if (insert<=0){
            return Result.badRequest("保存失败");
        }
        Result<String> result = Result.success(article.getId().toString());
        return result;
    }

    @GetMapping("article/{id}")
    public Result<ArticleVersion> articleGet(@PathVariable("id") Long id) {
        ArticleVersionExample articleVersionExample = new ArticleVersionExample();
        articleVersionExample.createCriteria().andArticleIdEqualTo(id);
        articleVersionExample.setOrderByClause("id desc");
        articleVersionService.se(articleVersionExample);

        Result<Article> result = Result.success(article);
        return result;
    }
}
