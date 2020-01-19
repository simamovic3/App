import { Component, OnInit } from '@angular/core';
import { Article } from '../../../models/article.model';
import { MyHttpService } from '../../../services/http.service';
import { Category } from '../../../models/category.model';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent  {

  public articles: Article[];  
  public categories: Category[];
  public showCategories: FormGroup;
  public category: string;
  public articlesToShow: Article[];

  constructor(protected httpService: MyHttpService, protected fb: FormBuilder) {

    this.showCategories = this.fb.group({ 
      'category' : [this.category] 
    });

    this.getCategories();
    this.getArticles();   
  }

  getArticles(){
    this.httpService.getArticles().subscribe(data => {
        this.articles = data;
        this.articlesToShow = data;
       // console.log(this.articles);
    });
  }

  getCategories(){
    this.httpService.getCategories().subscribe(data => {
        this.categories = data;
    });
  }

  getCat(cat: string){
    if(cat !== 'all') {
        this.articlesToShow = this.articles.filter(article => article.category.cattitle === cat);
    }
    else{
      this.articlesToShow = this.articles;
    }
  }
}
