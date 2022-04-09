package pro.liux.web.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static pro.liux.web.mapper.ArticleVersionDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import pro.liux.web.model.ArticleVersion;

@Mapper
public interface ArticleVersionMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ArticleVersion>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    BasicColumn[] selectList = BasicColumn.columnList(id, articleId, gmtCreate, gmtModified, title, content);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ArticleVersionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.BIGINT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="title", property="title", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ArticleVersion> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ArticleVersionResult")
    Optional<ArticleVersion> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default int insert(ArticleVersion row) {
        return MyBatis3Utils.insert(this::insert, row, articleVersion, c ->
            c.map(id).toProperty("id")
            .map(articleId).toProperty("articleId")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default int insertMultiple(Collection<ArticleVersion> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, articleVersion, c ->
            c.map(id).toProperty("id")
            .map(articleId).toProperty("articleId")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default int insertSelective(ArticleVersion row) {
        return MyBatis3Utils.insert(this::insert, row, articleVersion, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(articleId).toPropertyWhenPresent("articleId", row::getArticleId)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", row::getGmtCreate)
            .map(gmtModified).toPropertyWhenPresent("gmtModified", row::getGmtModified)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(content).toPropertyWhenPresent("content", row::getContent)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source Table: article_version")
    default Optional<ArticleVersion> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default List<ArticleVersion> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default List<ArticleVersion> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default Optional<ArticleVersion> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, articleVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    static UpdateDSL<UpdateModel> updateAllColumns(ArticleVersion row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(articleId).equalTo(row::getArticleId)
                .set(gmtCreate).equalTo(row::getGmtCreate)
                .set(gmtModified).equalTo(row::getGmtModified)
                .set(title).equalTo(row::getTitle)
                .set(content).equalTo(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ArticleVersion row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(articleId).equalToWhenPresent(row::getArticleId)
                .set(gmtCreate).equalToWhenPresent(row::getGmtCreate)
                .set(gmtModified).equalToWhenPresent(row::getGmtModified)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(content).equalToWhenPresent(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default int updateByPrimaryKey(ArticleVersion row) {
        return update(c ->
            c.set(articleId).equalTo(row::getArticleId)
            .set(gmtCreate).equalTo(row::getGmtCreate)
            .set(gmtModified).equalTo(row::getGmtModified)
            .set(title).equalTo(row::getTitle)
            .set(content).equalTo(row::getContent)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7720206+08:00", comments="Source Table: article_version")
    default int updateByPrimaryKeySelective(ArticleVersion row) {
        return update(c ->
            c.set(articleId).equalToWhenPresent(row::getArticleId)
            .set(gmtCreate).equalToWhenPresent(row::getGmtCreate)
            .set(gmtModified).equalToWhenPresent(row::getGmtModified)
            .set(title).equalToWhenPresent(row::getTitle)
            .set(content).equalToWhenPresent(row::getContent)
            .where(id, isEqualTo(row::getId))
        );
    }
}