package com.tim4.app.forum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid")
    private Integer answerid;

    @NotNull
    @Column(name = "text", length=500)
    private String text;

    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "questionid")
    private Question question;

    public  Answer(){}
     public Answer(String text, Question question){
        this.setText(text);
        this.setAnswerid(answerid);
        this.setQuestion(question);
    }

    public Integer getAnswerid() {
        return answerid;
    }

    public void setAnswerid(Integer answerid) {
        this.answerid = answerid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
