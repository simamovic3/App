package com.tim4.app.forum.controllers;

import com.tim4.app.forum.models.Question;
import com.tim4.app.forum.repositories.QuestionRepository;
import com.tim4.app.forum.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@CrossOrigin(origins = "*")
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    QuestionRepository questionRepository;
    QuestionService questionService;

    @Autowired
    public QuestionController(QuestionRepository questionRepository,QuestionService questionService){
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }


    //Dohvaća sva pitanja
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> getAll(){
        return questionService.getAllQuestions();
    }


    //Dodaje novo pitanje i vrati sva preostala radi provjere
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="/add")
    public ResponseEntity<?> addNew(@Valid @RequestParam String text) {
        return questionService.addNewQuestion(text);
    }

    //Dohvaća jedno pitanje sa traženim id-em
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return questionService.getOneQuestion(id);
    }


    //Briše jedno pitanje sa traženim id-em i vrati sva preostala radi provjere
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/deleteById")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        return questionService.deleteOneQuestion(id);
    }

    //Mijenja jedno pitanje sa traženim id-em i vrati to izmijenjeno pitanje
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT}, value = "/updateById")
    public ResponseEntity<?> update(@RequestParam Integer id, @RequestParam String newText) {
        return questionService.updateOneQuestion(id,newText);
    }

    //Vraća sva pitanja koja nemaju nijedan odgovor
    @RequestMapping(method = RequestMethod.GET, value = "/getQuestionWithNoAnswer")
    public ResponseEntity<?> getQWithNoAnswer() {
        return questionService.getQuestionsWithNoAnswer();
    }

    //Vraća sva pitanja čiji text sadrži dati string
    @RequestMapping(method = RequestMethod.GET, value = "/getQuestionByTextContaining")
    public ResponseEntity<?> getQuestionContainingText(@RequestParam String text) {
        return questionService.getQuestionContainingText(text);
    }
}

