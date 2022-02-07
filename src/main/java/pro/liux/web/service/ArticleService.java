package pro.liux.web.service;

import pro.liux.web.model.Article;

import java.util.List;

public interface ArticleService {


    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertOrUpdate(Article record);

    int insertOrUpdateSelective(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    int updateBatch(List<Article> list);

    int updateBatchSelective(List<Article> list);

    int batchInsert(List<Article> list);

}



