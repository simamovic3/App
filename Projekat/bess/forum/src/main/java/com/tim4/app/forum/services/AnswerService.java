package com.tim4.app.forum.services;

import com.tim4.app.forum.exceptions.ExceptionHandlerClass;
import com.tim4.app.forum.models.Answer;
import com.tim4.app.forum.models.Question;
import com.tim4.app.forum.repositories.AnswerRepository;
import com.tim4.app.forum.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class AnswerService {

    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    @Autowired
    public  AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository){
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;

        this.questionRepository.deleteAll();
        saveSomeQuestions();

        this.answerRepository.deleteAll();
        saveSomeAnswers();

    }

    private void saveSomeQuestions() {
        Question question = new Question("How can you design small spaces?");
        questionRepository.save(question);

        Question question1 = new Question("When designing a room what is the most important factor for you?");
        questionRepository.save(question1);

        Question question2 = new Question("How to fit bookshelves in a small space?");
        questionRepository.save(question2);
    }

    private void saveSomeAnswers() {

        Answer answer = new Answer("In a long living room, breakdown the space by making three separate seating areas with pieces of furniture that are small in scale",this.questionRepository.findByQuestionid(1));
        answerRepository.save(answer);

        Answer answer1 = new Answer("Use light color paint on the wall to make the space look bigger",this.questionRepository.findByQuestionid(1));
        answerRepository.save(answer1);

        Answer answer2 = new Answer("In a small bedroom, keep a bed that covers half of the space which makes the room space bigger",this.questionRepository.findByQuestionid(1));
        answerRepository.save(answer2);

        Answer answer3 = new Answer("Once the design or thought is clear in your mind about the design is to follow a theme and the design entire room accordingly which includes color, furniture and piece of art.",this.questionRepository.findByQuestionid(2));
        answerRepository.save(answer3);

        Answer answer4 = new Answer("If you are having small space and accommodating bookshelves is a problem. Then it is advisable to get a wall mounted bookshelves, and it will occupy less floor space compared to free-standing bookshelves.",this.questionRepository.findByQuestionid(3));
        answerRepository.save(answer4);
    }

    //Dohvaća sve odgovore
    public ResponseEntity<?> getAll(){
        Collection<Answer> answers = this.answerRepository.findAll();
        if(answers.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Answer>>(answers, HttpStatus.OK);
    }


    //Dodaje novi odgovor određenom pitanju i vrati sve odgovore radi provjere
    public ResponseEntity<?> addNewAnswer(String text,Integer id) {
        if (answerRepository.findByText(text).isPresent()) {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with text:"+text+" already exists."),
                    HttpStatus.CONFLICT);
        }
        else if (!questionRepository.existsById(id)) {

            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+id+" was not found."),
                    HttpStatus.CONFLICT);
        }

        Answer answer = new Answer(text, questionRepository.getOne(id));

        answerRepository.save(answer);
        Collection<Answer>answers = this.answerRepository.findAll();
        return new ResponseEntity<Collection<Answer>>(answers,HttpStatus.OK);

    }

    //Dohvaća jednan odgovor sa traženim id-em
    public ResponseEntity<?> getOneAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);

        if (!answer.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Answer>>(answer, HttpStatus.OK);
    }

    //Briše jednan odgovor sa traženim id-em i vrati sve preostale radi provjere
    public ResponseEntity<?> deleteOneAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);

        if (!answer.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        answerRepository.deleteById(id);
        Collection<Answer>answers = this.answerRepository.findAll();
        return new ResponseEntity<Collection<Answer>>(answers,HttpStatus.OK);
    }

    //Mijenja jednan odgovor sa traženim id-em i vrati taj izmijenjeni odgovor
    public ResponseEntity<?> updateOneAnswer(Integer id, String newText) {

        Optional<Answer> answer = this.answerRepository.findById(id);

        if (!answer.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        answer.get().setText(newText);
        answerRepository.save(answer.get());
        return new ResponseEntity<Answer>(answerRepository.getOne(id),HttpStatus.OK);
    }

    //Vraća sve odgovore jednog određenog pitanja datog id-em
    public ResponseEntity<?> getAllAnswerOfQuestion(Integer id) {

        if (!questionRepository.existsById(id))
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+id+" was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Answer>>(answerRepository.findByQuestion(questionRepository.getOne(id)),HttpStatus.OK);
    }

    //Vraća određeni odgovor određenog pitanja (oboje preko id polja)
    public ResponseEntity<?> getOneAnswerOfOneQuestion(Integer q_id, Integer a_id) {

        if (!questionRepository.existsById(q_id))
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Question with id:"+q_id+" was not found."), HttpStatus.NOT_FOUND);
        }
        else if(!answerRepository.existsById(a_id))
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with id:"+a_id+" for the question with id:"+q_id+" was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Answer>(answerRepository.findByQuestionAndAnswerid(questionRepository.getOne(q_id), a_id),HttpStatus.OK);
    }

    //Vraća odgovor po datom textu
    public ResponseEntity<?> getAnswerByItsText(String text) {

        Optional<Answer> answer = answerRepository.findByText(text);

        if (!answer.isPresent())
        {
            return new ResponseEntity(new ExceptionHandlerClass().DisplayErrorMessage("Answer with text:"+text+" does not exist."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Answer>>(answer, HttpStatus.OK);
    }

}
