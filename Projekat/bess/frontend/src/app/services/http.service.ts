import { FormGroup, Form,AbstractControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { catchError } from "rxjs/operators";

import { constructDependencies } from '@angular/core/src/di/reflective_provider';
import { Question } from '../models/question.model';
import { Article } from '../models/article.model';
import { Category } from '../models/category.model';
import { User } from '../models/user';
import { AuthInterceptor } from './auth-interceptor.service';
import { Answer } from '../models/answer.model';

@Injectable()
export class MyHttpService {

    public resp:boolean = false;
    public questions: Question[];

    constructor (private http: HttpClient, private router:Router, private interceptor: AuthInterceptor) {}

    public addQuestion(post){   
        this.http.post<any>('http://localhost:8084/forums/question/add?text=' + post.text,{})
          .subscribe((response: any) => {
            //alert('Question successfully created');
            this.router.navigate(['/forum']);
            },
          (err: any) => {console.error(err);});
    }

    public addAnswer(post, id){   
      this.http.post<any>('http://localhost:8084/forums/answer/add?text=' + post.text + '&id='+ id,{})
        .subscribe((response: any) => {
          //alert('Question successfully created');
          this.router.navigate(['/forum']);
          },
        (err: any) => {console.error(err);});
  }
    public getQuestions(){
      return this.http.get<Question>('http://localhost:8084/forums/question/getAll')
                .pipe(map((response: any) => response));                   
    }

    public getArticles(){
      return this.http.get<Article>('http://localhost:8084/articles/articles/getAll')
                .pipe(map((response: any) => response));                   
    }

    public getCategories(){
      return this.http.get<Category>('http://localhost:8084/articles/categories/getAll')
                .pipe(map((response: any) => response));                   
    }

    public addUser(post){        
      this.http.post<any>('http://localhost:8092/users/user/add?name=' + post.name, + '&lastName=' + post.lastName + '&email=' + post.email + '&username=' + post.username + '&password=' + post.password + '&age=' + post.age, {})
        .subscribe((response: any) => {
          alert('Question successfully created');
          this.router.navigate(['/questions']);
          },
        (err: any) => {console.error(err);});
  }

  public getUsers(){
    return this.interceptor.get('http://localhost:8092/users/all')
          .pipe(map((response: any) => response));
  }

  public deleteUser(username: string){
    return this.interceptor.get('http://localhost:8092/users/delete/' + username)
              .pipe(map((response: any) => response));                   
  }

  public getArticlesByUsername(){
    console.log( localStorage.getItem("logged_user"));
    return this.http.get<Article>('http://localhost:8084/articles/articles/getByUsername/' + localStorage.getItem("logged_user"))
              .pipe(map((response: any) => response));                   
  }


}