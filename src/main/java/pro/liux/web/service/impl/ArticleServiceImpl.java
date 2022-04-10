package pro.liux.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.liux.web.mapper.ArticleDetailDynamicSqlSupport;
import pro.liux.web.mapper.ArticleDetailMapper;
import pro.liux.web.mapper.ArticleMapper;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleDetail;
import pro.liux.web.service.ArticleService;
import pro.liux.web.utils.IdWorker;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.mybatis.dynamic.sql.SqlBuilder.*;  // import MyBatis Dynamic SQL where support
import static pro.liux.web.mapper.ArticleDetailDynamicSqlSupport.*;
import static pro.liux.web.mapper.ArticleDynamicSqlSupport.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    IdWorker idWorker;

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleDetailMapper articleDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveArticleDetail(ArticleDetail articleDetail, Boolean publish) {
        long generateId = idWorker.nextId();
        articleDetail.setId(generateId);
        LocalDateTime now = LocalDateTime.now();
        articleDetail.setGmtCreate(now);
        articleDetail.setGmtModified(now);
        int insert = articleDetailMapper.insert(articleDetail);
        if (!publish) {
            return insert > 0;
        }
        return publish(articleDetail);

    }

    public Boolean publish(ArticleDetail articleDetail) {
        Article article = new Article();
        article.setId(articleDetail.getArticleId());
        article.setGmtModified(articleDetail.getGmtModified());
        article.setTitle(articleDetail.getTitle());
        //todo content提取mainContent
        article.setMainContent(articleDetail.getContent());
        int i = articleMapper.updateByPrimaryKeySelective(article);
        return i > 0;
    }

    @Override
    public Boolean checkArticleExist(Long articleId) {
        Optional<Article> article = articleMapper.selectByPrimaryKey(articleId);
        return article.isPresent();
    }

    @Override
    public ArticleDetail getLatestArticleDetail(Long _articleId) {
        Optional<ArticleDetail> articleDetail = articleDetailMapper.selectOne(
                c -> c.where(articleId, isEqualTo(_articleId))
                        .orderBy(ArticleDetailDynamicSqlSupport.id.descending()).limit(1));
        return articleDetail.orElse(null);
    }
}














