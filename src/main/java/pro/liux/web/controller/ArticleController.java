package pro.liux.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.model.ArticleDetail;
import pro.liux.web.service.ArticleService;
import pro.liux.web.service.FileService;
import pro.liux.web.utils.IdWorker;
import pro.liux.web.vo.Result;


@RestController
@CrossOrigin
@RequestMapping("api")
public class ArticleController {
    @Autowired
    IdWorker idWorker;

    @Autowired
    ArticleService articleService;
    @Autowired
    FileService fileService;

    /**
     * vditor 上传文章到版本库
     *
     * @param article 文章
     */
    @PostMapping("article/{articleId}")
    public Result<String> articleSave(@RequestBody @Validated ArticleDetail article,
                                      @PathVariable("articleId") Long articleId,
                                      @RequestParam(value = "publish", required = false, defaultValue = "false") Boolean publish
    ) {
        article.setArticleId(articleId);
        if (!articleService.checkArticleExist(articleId)){
            return Result.fail("articleId不存在");
        }
        Boolean ret = articleService.saveArticleDetail(article, publish);
        if (ret) {
            return Result.success(article.getId().toString());
        } else {
            return Result.fail("保存失败");
        }
    }

    /**
     * @param articleId article id
     * @return article id 对应的最新 ArticleDetail ,并不一定是当前article绑定已发布的detail
     */
    @GetMapping("article/{articleId}")
    public Result<ArticleDetail> articleGetLatest(@PathVariable("articleId") Long articleId) {
        ArticleDetail latestArticleDetail = articleService.getLatestArticleDetail(articleId);
        return Result.success(latestArticleDetail);
    }
}
