package pro.liux.web.service;

import java.util.List;
import pro.liux.web.model.ArticleVersion;import pro.liux.web.model.ArticleVersionExample;

public interface ArticleVersionService {


    int deleteByPrimaryKey(Long id);

    int insert(ArticleVersion record);

    int insertOrUpdate(ArticleVersion record);

    int insertOrUpdateSelective(ArticleVersion record);

    int insertSelective(ArticleVersion record);

    ArticleVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleVersion record);

    int updateByPrimaryKey(ArticleVersion record);

    int updateBatch(List<ArticleVersion> list);

    int updateBatchSelective(List<ArticleVersion> list);

    int batchInsert(List<ArticleVersion> list);

    long countByExample(ArticleVersionExample example);

    int deleteByExample(ArticleVersionExample example);

    List<ArticleVersion> selectByExample(ArticleVersionExample example);

    int updateByExampleSelective(ArticleVersion record, ArticleVersionExample example);

    int updateByExample(ArticleVersion record, ArticleVersionExample example);
}





