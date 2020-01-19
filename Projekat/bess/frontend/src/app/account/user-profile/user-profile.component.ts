import { Component, OnInit } from '@angular/core';
import { Article } from '../../models/article.model';
import { MyHttpService } from '../../services/http.service';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public articles: Article[]; 

  constructor(protected httpService: MyHttpService, protected fb: FormBuilder) {
    this.getArticlesByUsername();
   }

  ngOnInit() {
  }

  getArticlesByUsername(){
    this.httpService.getArticlesByUsername().subscribe(data => {
        this.articles = data;
        
    });
  }

}
