package pro.liux.web.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static pro.liux.web.mapper.ArticleDynamicSqlSupport.*;

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
import pro.liux.web.model.Article;

@Mapper
public interface ArticleMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Article>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.762683+08:00", comments="Source Table: article")
    BasicColumn[] selectList = BasicColumn.columnList(id, articleVersionId, gmtCreate, gmtModified, isDelete, title, mainContent);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7536622+08:00", comments="Source Table: article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ArticleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="article_version_id", property="articleVersionId", jdbcType=JdbcType.BIGINT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.BIT),
        @Result(column="title", property="title", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="main_content", property="mainContent", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Article> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7556716+08:00", comments="Source Table: article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ArticleResult")
    Optional<Article> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7566662+08:00", comments="Source Table: article")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7576751+08:00", comments="Source Table: article")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7576751+08:00", comments="Source Table: article")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7586795+08:00", comments="Source Table: article")
    default int insert(Article row) {
        return MyBatis3Utils.insert(this::insert, row, article, c ->
            c.map(id).toProperty("id")
            .map(articleVersionId).toProperty("articleVersionId")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(isDelete).toProperty("isDelete")
            .map(title).toProperty("title")
            .map(mainContent).toProperty("mainContent")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7606714+08:00", comments="Source Table: article")
    default int insertMultiple(Collection<Article> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, article, c ->
            c.map(id).toProperty("id")
            .map(articleVersionId).toProperty("articleVersionId")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(isDelete).toProperty("isDelete")
            .map(title).toProperty("title")
            .map(mainContent).toProperty("mainContent")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7616652+08:00", comments="Source Table: article")
    default int insertSelective(Article row) {
        return MyBatis3Utils.insert(this::insert, row, article, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(articleVersionId).toPropertyWhenPresent("articleVersionId", row::getArticleVersionId)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", row::getGmtCreate)
            .map(gmtModified).toPropertyWhenPresent("gmtModified", row::getGmtModified)
            .map(isDelete).toPropertyWhenPresent("isDelete", row::getIsDelete)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(mainContent).toPropertyWhenPresent("mainContent", row::getMainContent)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7636646+08:00", comments="Source Table: article")
    default Optional<Article> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7636646+08:00", comments="Source Table: article")
    default List<Article> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7646791+08:00", comments="Source Table: article")
    default List<Article> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7646791+08:00", comments="Source Table: article")
    default Optional<Article> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7646791+08:00", comments="Source Table: article")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, article, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7656668+08:00", comments="Source Table: article")
    static UpdateDSL<UpdateModel> updateAllColumns(Article row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(articleVersionId).equalTo(row::getArticleVersionId)
                .set(gmtCreate).equalTo(row::getGmtCreate)
                .set(gmtModified).equalTo(row::getGmtModified)
                .set(isDelete).equalTo(row::getIsDelete)
                .set(title).equalTo(row::getTitle)
                .set(mainContent).equalTo(row::getMainContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7656668+08:00", comments="Source Table: article")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Article row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(articleVersionId).equalToWhenPresent(row::getArticleVersionId)
                .set(gmtCreate).equalToWhenPresent(row::getGmtCreate)
                .set(gmtModified).equalToWhenPresent(row::getGmtModified)
                .set(isDelete).equalToWhenPresent(row::getIsDelete)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(mainContent).equalToWhenPresent(row::getMainContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7666741+08:00", comments="Source Table: article")
    default int updateByPrimaryKey(Article row) {
        return update(c ->
            c.set(articleVersionId).equalTo(row::getArticleVersionId)
            .set(gmtCreate).equalTo(row::getGmtCreate)
            .set(gmtModified).equalTo(row::getGmtModified)
            .set(isDelete).equalTo(row::getIsDelete)
            .set(title).equalTo(row::getTitle)
            .set(mainContent).equalTo(row::getMainContent)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7676797+08:00", comments="Source Table: article")
    default int updateByPrimaryKeySelective(Article row) {
        return update(c ->
            c.set(articleVersionId).equalToWhenPresent(row::getArticleVersionId)
            .set(gmtCreate).equalToWhenPresent(row::getGmtCreate)
            .set(gmtModified).equalToWhenPresent(row::getGmtModified)
            .set(isDelete).equalToWhenPresent(row::getIsDelete)
            .set(title).equalToWhenPresent(row::getTitle)
            .set(mainContent).equalToWhenPresent(row::getMainContent)
            .where(id, isEqualTo(row::getId))
        );
    }
}