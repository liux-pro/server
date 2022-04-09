package pro.liux.web.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;

public class ArticleVersion {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.article_id")
    private Long articleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.gmt_create")
    private LocalDateTime gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.gmt_modified")
    private LocalDateTime gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.article_id")
    public Long getArticleId() {
        return articleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.article_id")
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7686795+08:00", comments="Source field: article_version.gmt_create")
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.770003+08:00", comments="Source field: article_version.gmt_create")
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.gmt_modified")
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.gmt_modified")
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-08T22:06:54.7700158+08:00", comments="Source field: article_version.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}