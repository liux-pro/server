package pro.liux.web.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ArticleVersionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source Table: article_version")
    public static final ArticleVersion articleVersion = new ArticleVersion();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.id")
    public static final SqlColumn<Long> id = articleVersion.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.article_id")
    public static final SqlColumn<Long> articleId = articleVersion.articleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.gmt_create")
    public static final SqlColumn<LocalDateTime> gmtCreate = articleVersion.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source field: article_version.gmt_modified")
    public static final SqlColumn<LocalDateTime> gmtModified = articleVersion.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source field: article_version.title")
    public static final SqlColumn<String> title = articleVersion.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7710141+08:00", comments="Source field: article_version.content")
    public static final SqlColumn<String> content = articleVersion.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source Table: article_version")
    public static final class ArticleVersion extends AliasableSqlTable<ArticleVersion> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> articleId = column("article_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

        public final SqlColumn<String> title = column("title", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public ArticleVersion() {
            super("article_version", ArticleVersion::new);
        }
    }
}