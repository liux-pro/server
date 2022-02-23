package pro.liux.web.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pro.liux.web.model.ArticleVersion;

@Mapper
public interface ArticleVersionMapper {
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
    int insert(ArticleVersion record);

    int insertOrUpdate(ArticleVersion record);

    int insertOrUpdateSelective(ArticleVersion record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ArticleVersion record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ArticleVersion selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ArticleVersion record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ArticleVersion record);

    int updateBatch(List<ArticleVersion> list);

    int updateBatchSelective(List<ArticleVersion> list);

    int batchInsert(@Param("list") List<ArticleVersion> list);
}