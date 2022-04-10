package pro.liux.web.model;

import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import javax.annotation.Generated;

@RedisHash("Article")
public class Article {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5003322+08:00", comments="Source field: article.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.article_detail_id")
    private Long articleDetailId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_create")
    private LocalDateTime gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_modified")
    private LocalDateTime gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5033327+08:00", comments="Source field: article.is_delete")
    private Boolean isDelete;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5033327+08:00", comments="Source field: article.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5035506+08:00", comments="Source field: article.main_content")
    private String mainContent;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5013322+08:00", comments="Source field: article.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.article_detail_id")
    public Long getArticleDetailId() {
        return articleDetailId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.article_detail_id")
    public void setArticleDetailId(Long articleDetailId) {
        this.articleDetailId = articleDetailId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_create")
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_create")
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_modified")
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5023345+08:00", comments="Source field: article.gmt_modified")
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5033327+08:00", comments="Source field: article.is_delete")
    public Boolean getIsDelete() {
        return isDelete;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5033327+08:00", comments="Source field: article.is_delete")
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5033327+08:00", comments="Source field: article.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5035506+08:00", comments="Source field: article.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5035506+08:00", comments="Source field: article.main_content")
    public String getMainContent() {
        return mainContent;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-11T01:36:21.5035506+08:00", comments="Source field: article.main_content")
    public void setMainContent(String mainContent) {
        this.mainContent = mainContent == null ? null : mainContent.trim();
    }
}