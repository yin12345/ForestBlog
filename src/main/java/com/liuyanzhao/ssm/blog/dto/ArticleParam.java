package com.liuyanzhao.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 言曌
 * @date 2018/11/25 下午3:56
 */
@Data
public class ArticleParam {
    private Integer articleUserId;

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private List<Integer> articleTagIds;

    private String articleImg;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getArticleParentCategoryId() {
        return articleParentCategoryId;
    }

    public void setArticleParentCategoryId(Integer articleParentCategoryId) {
        this.articleParentCategoryId = articleParentCategoryId;
    }

    public Integer getArticleChildCategoryId() {
        return articleChildCategoryId;
    }

    public void setArticleChildCategoryId(Integer articleChildCategoryId) {
        this.articleChildCategoryId = articleChildCategoryId;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public List<Integer> getArticleTagIds() {
        return articleTagIds;
    }

    public void setArticleTagIds(List<Integer> articleTagIds) {
        this.articleTagIds = articleTagIds;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

}
