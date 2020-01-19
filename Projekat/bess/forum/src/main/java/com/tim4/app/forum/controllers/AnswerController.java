package com.tim4.app.forum.controllers;

import com.tim4.app.forum.exceptions.ExceptionHandlerClass;
import com.tim4.app.forum.models.Answer;
import com.tim4.app.forum.models.Question;
import com.tim4.app.forum.repositories.AnswerRepository;
import com.tim4.app.forum.repositories.QuestionRepository;
import com.tim4.app.forum.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.MediaType;

@CrossOrigin(origins = "*")
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(value = "/answer")
public class AnswerController {


    AnswerService answerService;
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    @Autowired
    public AnswerController( AnswerService answerService, AnswerRepository answerRepository, QuestionRepository questionRepository){
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }
    //Dohvaća sve odgovore
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> getAll(){
        return answerService.getAll();
    }


    //Dodaje novi odgovor određenom pitanju i vrati sve odgovore radi provjere
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="/add")
    public ResponseEntity<?> addNewAnswer(@Valid @RequestParam String text, @RequestParam Integer id) {
        return answerService.addNewAnswer(text,id);
    }

    //Dohvaća jednan odgovor sa traženim id-em
    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<?> getOneAnswer(@RequestParam Integer id) {
        return answerService.getOneAnswer(id);
    }

    //Briše jednan odgovor sa traženim id-em i vrati sve preostale radi provjere
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/deleteById")
    public ResponseEntity<?> deleteOneAnswer(@RequestParam Integer id) {
        return answerService.deleteOneAnswer(id);
    }

    //Mijenja jednan odgovor sa traženim id-em i vrati taj izmijenjeni odgovor
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT}, value = "/updateById")
    public ResponseEntity<?> updateOneAnswer(@RequestParam Integer id, @RequestParam String newText) {
        return answerService.updateOneAnswer(id,newText);
    }

    //Vraća sve odgovore jednog određenog pitanja datog id-em
    @RequestMapping(method = RequestMethod.GET, value = "/getByQuestionId")
    public ResponseEntity<?> getAllAnswerOfQuestion(@RequestParam Integer id) {
        return answerService.getAllAnswerOfQuestion(id);
    }

    //Vraća određeni odgovor određenog pitanja (oboje preko id polja)
    @RequestMapping(method = RequestMethod.GET, value = "/getOneByQuestionId")
    public ResponseEntity<?> getOneAnswerOfOneQuestion(@RequestParam Integer q_id, @RequestParam Integer a_id) {
        return answerService.getOneAnswerOfOneQuestion(q_id,a_id);
    }

    //Vraća odgovor po datom textu
    @RequestMapping(method = RequestMethod.GET, value = "/getAnswerByText")
    public ResponseEntity<?> getAnswerByItsText(@RequestParam String text) {
        return answerService.getAnswerByItsText(text);
    }


}


