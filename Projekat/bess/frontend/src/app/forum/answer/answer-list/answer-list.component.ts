import { Component, OnInit } from '@angular/core';
import { Answer } from '../../../models/answer.model';
import { MyHttpService } from '../../../services/http.service';

@Component({
  selector: 'app-answer-list',
  templateUrl: './answer-list.component.html',
  styleUrls: ['./answer-list.component.scss']
})
export class AnswerListComponent{

  public answers: Answer[];

  constructor(protected httpService: MyHttpService) {
  }
}
