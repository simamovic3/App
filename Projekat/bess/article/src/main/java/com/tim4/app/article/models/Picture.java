package com.tim4.app.article.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pictureId")
    private Integer pictureId;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "pictureUrl")
    private String pictureUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    public Picture() {
    }

    public Picture(String title, String pictureUrl, Article article) {
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.article = article;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
