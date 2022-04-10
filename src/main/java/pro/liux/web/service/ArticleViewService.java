package pro.liux.web.service;


import org.springframework.web.bind.annotation.RequestParam;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleDetail;
import pro.liux.web.vo.Page;

/**
 * 对游客提供浏览
 */
public interface ArticleViewService {
    Page<Article> getPage(Long pageSize, Long pageNum);

    /**
     *
     * @param pageSize
     * @param pageNum
     * @param showHide 是否显示未发布的文章
     * @return
     */
    Page<Article> getPage(Long pageSize, Long pageNum, Boolean showHide);
    /**
     * 获取已发布的detail
     * @param articleId
     * @return
     */
    ArticleDetail getPublishedArticleDetail(Long articleId);
}





