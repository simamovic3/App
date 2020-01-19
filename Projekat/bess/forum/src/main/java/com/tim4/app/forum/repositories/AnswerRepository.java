package com.tim4.app.forum.repositories;

import com.tim4.app.forum.models.Answer;
import com.tim4.app.forum.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository <Answer, Integer> {
    Optional<Answer> findById(Integer id);
    Optional<Answer> findByText(String text);
    Collection<Answer> findByQuestion(Question question);
    Answer findByQuestionAndAnswerid(Question question, Integer id);
}
