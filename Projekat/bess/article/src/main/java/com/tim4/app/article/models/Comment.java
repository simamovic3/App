package com.tim4.app.article.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Integer commentId;

    @NotNull
    @Column(name = "text")
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Comment() {
    }

    public Comment(String text, Article article, User user) {

        this.text = text;
        this.article = article;
        this.user = user;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
