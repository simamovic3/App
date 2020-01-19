import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MyHttpService } from '../../services/http.service';
import { Router } from '@angular/router';
import { Category } from '../../models/category.model';
import { ArticleService } from './article.service';
import { HttpClient } from '@angular/common/http';
import 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent {

  public addArticleForm: FormGroup;

  title = '';
  description = '';
  category = '';
  file: File = null;
  url = 'http://placehold.it/700x400';

  public categories: Category[];

  getCategories(){
    this.httpService.getCategories().subscribe(data => {
        this.categories = data;
    });
  }

  constructor(protected fb: FormBuilder, protected httpService: MyHttpService, protected articleService: ArticleService, private http: HttpClient) {

    this.addArticleForm = this.fb.group({
      'title': [this.title, Validators.required],
      'description': [this.description, Validators.required],
      'category' : [this.category, Validators.required] ,
      'user': null,
      'url' : null

    });

    this.getCategories();
  }

  addArticle(article, url) {
    console.log(article);
    this.articleService.addArticle(article, url);
    this.addArticleForm.reset();
   }

   onFileChanged(event){
     this.file = <File>event.target.files[0];
     console.log(this.file);

    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
          this.url = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
  }
   }

   onUpload(article){
    
      var form = new FormData()
      form.append('image', this.file, this.file.name)

      const config = {
        headers: {
          'Authorization': 'Client-ID ' + 'bed2d1112b7523c'
        }
      }

      this.http.post('https://api.imgur.com/3/image', form, config).subscribe((data: any ) => {
        console.log(data.data.link);
        this.url = data.data.link;
        this.addArticle(article, data.data.link);
      });

 

    }

  }



