package com.tim4.app.forum.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionid")
    private Integer questionid;

    @NotNull
    @Column(name = "text", length=500)
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    public  Question(){}

    public Question(String text){
        this.setText(text);

    }


    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public String validate()
    {
        if(this.text.isEmpty())
            return "Text of the question can not stay empty.";
        else if(this.text.length()>1000)
            return "Text is too long.";
        return "";
    }
}
