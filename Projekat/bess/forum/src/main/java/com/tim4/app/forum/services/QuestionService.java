package com.tim4.app.forum.services;
import com.tim4.app.forum.exceptions.ExceptionHandlerClass;
import com.tim4.app.forum.models.Question;
import com.tim4.app.forum.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //Dohvaća sva pitanja
    public ResponseEntity<?> getAllQuestions(){
        Collection<Question> questions = this.questionRepository.findAll();
        if(questions.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Question>>(questions, HttpStatus.OK);
    }

    //Dodaje novo pitanje i vrati sva preostala radi provjere
    public ResponseEntity<?> addNewQuestion(String text) {
        Question question = new Question(text);
        if(!question.validate().isEmpty()){
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage(question.validate()),
                    HttpStatus.CONFLICT);
        }
        else if (questionRepository.findByText(text).isPresent()) {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with text:"+text+" already exists."),
                    HttpStatus.CONFLICT);
        } else {
            questionRepository.save(question);
            Collection<Question>questions = this.questionRepository.findAll();
            return new ResponseEntity<Collection<Question>>(questions,HttpStatus.OK);
        }
    }


    //Dohvaća jedno pitanje sa traženim id-em
    public ResponseEntity<?> getOneQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (!question.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Question>>(question, HttpStatus.OK);
    }


    //Briše jedno pitanje sa traženim id-em i vrati sva preostala radi provjere
    public ResponseEntity<?> deleteOneQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (!question.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        questionRepository.deleteById(id);
        Collection<Question>questions = this.questionRepository.findAll();
        return new ResponseEntity<Collection<Question>>(questions,HttpStatus.OK);
    }

    //Mijenja jedno pitanje sa traženim id-em i vrati to izmijenjeno pitanje
    public ResponseEntity<?> updateOneQuestion(Integer id,String newText) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (!question.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        question.get().setText(newText);
        if(!question.get().validate().isEmpty()){
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage(question.get().validate()),
                    HttpStatus.CONFLICT);
        }
        questionRepository.save(question.get());
        return new ResponseEntity<Question>(questionRepository.getOne(id),HttpStatus.OK);
    }

    //Vraća sva pitanja koja nemaju nijedan odgovor
    public ResponseEntity<?> getQuestionsWithNoAnswer() {
        Collection<Question> questions = questionRepository.findByAnswersIsNull();

        if (questions.isEmpty())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Each question has been answered."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Question>>(questions, HttpStatus.OK);
    }

    //Vraća sva pitanja čiji text sadrži dati string
    public ResponseEntity<?> getQuestionContainingText(String text) {
        Collection<Question> questions = questionRepository.findByTextContaining("%"+text+"%");

        if (questions.isEmpty())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Non of the questions contain text:"+text+" ."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Question>>(questions, HttpStatus.OK);
    }
}
