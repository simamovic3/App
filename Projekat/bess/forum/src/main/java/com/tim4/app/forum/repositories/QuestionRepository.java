package com.tim4.app.forum.repositories;

import com.tim4.app.forum.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository <Question, Integer>{
    Optional<Question> findById(Integer id);
    Optional<Question> findByText(String text);
    Collection<Question> findByTextContaining(String text);
    Collection<Question> findByAnswersIsNull();
    Question findByQuestionid(Integer id);
}
