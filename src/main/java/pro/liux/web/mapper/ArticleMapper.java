package pro.liux.web.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pro.liux.web.model.Article;
import pro.liux.web.model.ArticleExample;

@Mapper
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Article record);

    int insertOrUpdate(Article record);

    int insertOrUpdateSelective(Article record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Article selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Article record);

    int updateBatch(List<Article> list);

    int batchInsert(@Param("list") List<Article> list);

    List<Article> selectArticleList(Article article);

    int updateBatchSelective(List<Article> list);
}