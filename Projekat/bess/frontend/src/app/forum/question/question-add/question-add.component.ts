import { Component, OnInit } from '@angular/core';
import { MyHttpService } from '../../../services/http.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-question-add',
  templateUrl: './question-add.component.html',
  styleUrls: ['./question-add.component.scss']
})
export class QuestionAddComponent implements OnInit {

  public questionCreateForm:FormGroup;
 
  constructor(private fb:FormBuilder ,private myHttpService: MyHttpService, private router: Router) {}
   ngOnInit(): void {
        
    this.questionCreateForm = this.fb.group({
        text: new FormControl('')
    });
}
  createQuestion(post) {
    console.log(post);
    this.myHttpService.addQuestion(post);
    this.questionCreateForm.reset();
    window.location.reload();

  }
}
