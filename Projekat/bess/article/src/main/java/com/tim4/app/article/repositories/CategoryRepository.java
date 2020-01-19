package com.tim4.app.article.repositories;

import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCattitle(String Cattitle);
}
