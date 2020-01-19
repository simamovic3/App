package com.tim4.app.article.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleId")
    private Integer articleId;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description", length=1000)
    private String description;

    @Column(name = "date")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cattitle")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @NotNull
    @JoinColumn(name = "url")
    private String pictureUrl;

    public Article() {}

    public Article(String title, String description, Category category, User user, String pictureUrl) {
        this.setTitle(title);
        this.setDescription(description);
        this.setCategory(category);
        this.setUser(user);
      //  this.setDate(date);
        this.setPictureUrl(pictureUrl);
    }

    public Article(String title, String description, Category category, User user, Date date, String pictureUrl) {
        this.setTitle(title);
        this.setDescription(description);
        this.setCategory(category);
        this.setUser(user);
        this.setDate(date);
        this.setPictureUrl(pictureUrl);
    }


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


}
