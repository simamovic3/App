package com.tim4.app.article.repositories;

import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Collection<Article> findByCategory(String category);
    Collection<Article> findByUser(User user);
}