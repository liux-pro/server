package pro.liux.web.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ArticleDetailDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source Table: article_detail")
    public static final ArticleDetail articleDetail = new ArticleDetail();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.id")
    public static final SqlColumn<Long> id = articleDetail.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.article_id")
    public static final SqlColumn<Long> articleId = articleDetail.articleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.gmt_create")
    public static final SqlColumn<LocalDateTime> gmtCreate = articleDetail.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.gmt_modified")
    public static final SqlColumn<LocalDateTime> gmtModified = articleDetail.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.title")
    public static final SqlColumn<String> title = articleDetail.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.content")
    public static final SqlColumn<String> content = articleDetail.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source Table: article_detail")
    public static final class ArticleDetail extends AliasableSqlTable<ArticleDetail> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> articleId = column("article_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

        public final SqlColumn<String> title = column("title", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public ArticleDetail() {
            super("article_detail", ArticleDetail::new);
        }
    }
}