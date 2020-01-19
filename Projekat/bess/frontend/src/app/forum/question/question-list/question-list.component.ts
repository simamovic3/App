import { Component, OnInit } from '@angular/core';
import { Question } from '../../../models/question.model';
import { MyHttpService } from '../../../services/http.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.scss']
})
export class QuestionListComponent implements OnInit{

  public answerCreateForm:FormGroup;


  public questions: Question[];
  public temp=false;
  public questionId=null;

  constructor(private fb:FormBuilder, protected httpService: MyHttpService) {
    this.getQuestions();

  }

  ngOnInit(): void {
        
    this.answerCreateForm = this.fb.group({
        text: new FormControl('')
    });
  }

  getQuestions(){
    this.httpService.getQuestions().subscribe(data => {
        this.questions = data;
    });
  }

  loadForm(id){
    this.temp=true;
    this.questionId=id;
  }

  dontAnswer(){
    this.temp=false;
  }

  createAnswer(post, id) {
    console.log(post);
    this.httpService.addAnswer(post, id);
    this.answerCreateForm.reset();
    this.temp=false;
    window.location.reload();
  }


}
