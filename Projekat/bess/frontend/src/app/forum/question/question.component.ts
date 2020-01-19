import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup,FormBuilder,Validators} from '@angular/forms';
import {Router} from "@angular/router";
import { MyHttpService } from '../../services/http.service';
import { Question } from '../../models/question.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {

  text = '';
  public questions:Question[];
  public questionForm:FormGroup;

  constructor(protected fb: FormBuilder, protected httpService: MyHttpService) {
    this.questionForm = this.fb.group({
      'text': [this.text]
    });
    this.getQuestions();
  }

  getQuestions(){
    this.httpService.getQuestions().subscribe(data => {
        this.questions = data;
       console.log(this.questions);
    });
  }
}