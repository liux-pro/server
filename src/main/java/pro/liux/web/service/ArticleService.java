package pro.liux.web.service;

import pro.liux.web.model.ArticleDetail;

public interface ArticleService {

    /**
     * 保存文章
     * @param article 文章 articleId必须有
     * @param publish 是否直接发布
     * @return
     */
    Boolean saveArticleDetail(ArticleDetail article, Boolean publish);

    /**
     * 发布 （articleDetail 同步到 article ）
     * @param articleDetail
     * @return
     */
    Boolean publish(ArticleDetail articleDetail);

    /**
     * 检查article是否存在
     * @param articleId
     * @return
     */
    Boolean checkArticleExist(Long articleId);

    ArticleDetail getLatestArticleDetail(Long articleId);
}














