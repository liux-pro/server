package pro.liux.web.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;

public class ArticleDetail {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.article_id")
    private Long articleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.gmt_create")
    private LocalDateTime gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.gmt_modified")
    private LocalDateTime gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.article_id")
    public Long getArticleId() {
        return articleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.article_id")
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.gmt_create")
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.gmt_create")
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5268812+08:00", comments="Source field: article_detail.gmt_modified")
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.gmt_modified")
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5278816+08:00", comments="Source field: article_detail.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}