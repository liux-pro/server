package pro.liux.web.service;

import java.util.List;
import pro.liux.web.model.ArticleVersion;

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

}


