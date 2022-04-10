package pro.liux.web.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ArticleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5063492+08:00", comments="Source Table: article")
    public static final Article article = new Article();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5063492+08:00", comments="Source field: article.id")
    public static final SqlColumn<Long> id = article.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.article_detail_id")
    public static final SqlColumn<Long> articleDetailId = article.articleDetailId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.gmt_create")
    public static final SqlColumn<LocalDateTime> gmtCreate = article.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.gmt_modified")
    public static final SqlColumn<LocalDateTime> gmtModified = article.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.is_delete")
    public static final SqlColumn<Boolean> isDelete = article.isDelete;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.title")
    public static final SqlColumn<String> title = article.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5073343+08:00", comments="Source field: article.main_content")
    public static final SqlColumn<String> mainContent = article.mainContent;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5063492+08:00", comments="Source Table: article")
    public static final class Article extends AliasableSqlTable<Article> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> articleDetailId = column("article_detail_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

        public final SqlColumn<Boolean> isDelete = column("is_delete", JDBCType.BIT);

        public final SqlColumn<String> title = column("title", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> mainContent = column("main_content", JDBCType.LONGVARCHAR);

        public Article() {
            super("article", Article::new);
        }
    }
}