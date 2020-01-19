import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup,FormBuilder,Validators} from '@angular/forms';
import {Router} from "@angular/router";
import { MyHttpService } from '../../services/http.service';
import { Answer } from '../../models/answer.model';

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})
export class AnswerComponent {

  text = '';
  public answers:Answer[];
  public answerForm:FormGroup;

  constructor(protected fb: FormBuilder, protected httpService: MyHttpService) {
    this.answerForm = this.fb.group({
      'text': [this.text]
    });
    
  }
}