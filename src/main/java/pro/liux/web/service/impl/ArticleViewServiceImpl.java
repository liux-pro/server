package pro.liux.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.liux.web.mapper.ArticleDetailMapper;
import pro.liux.web.mapper.ArticleDynamicSqlSupport;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleDetail;
import pro.liux.web.service.ArticleViewService;
import pro.liux.web.vo.Page;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static pro.liux.web.mapper.ArticleDetailDynamicSqlSupport.id;

@Service
public class ArticleViewServiceImpl implements ArticleViewService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleDetailMapper articleDetailMapper;

    @Override
    public Page<Article> getPage(Long pageSize, Long pageNum) {
        return getPage(pageSize, pageNum, false);
    }

    @Override
    public Page<Article> getPage(Long pageSize, Long pageNum, Boolean showHide) {
        List<Article> articles = articleMapper.select(c ->
                c.where(ArticleDynamicSqlSupport.isDelete, isEqualTo(false).filter((item)->!showHide))
                        .orderBy(id.descending())
                        .limit(pageSize).offset(pageSize * (pageNum - 1))
        );
        long count = articleMapper.count(c -> c.where(ArticleDynamicSqlSupport.isDelete, isEqualTo(false).filter((item)->!showHide)));

        Page<Article> articlePage = new Page<>(articles);
        articlePage.setTotal(count);
        return articlePage;
    }

    @Override
    public ArticleDetail getPublishedArticleDetail(Long _articleId) {
        Optional<Article> article = articleMapper.selectByPrimaryKey(_articleId);
        if (article.isPresent() && !article.get().getIsDelete()) {
            Long articleDetailId = article.get().getArticleDetailId();
            return articleDetailMapper.selectByPrimaryKey(articleDetailId).orElse(new ArticleDetail());
        }
        return new ArticleDetail();
    }
}





