import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { User } from "../../models/user";
import { UserArticle } from "../../models/user-article.model";
import { map } from "rxjs/operators";

@Injectable()
export class ArticleService {

    constructor (private http: HttpClient, private router:Router){}

	public addArticle(art, url){    

        let logged_user = new UserArticle(localStorage.getItem('logged_user'));
        console.log(logged_user);
        console.log(art.category);
        art.user= logged_user; 
        console.log(art.url);

        const jsonToSend = {title: art.title, description: art.description, pictureUrl: url, category: {cattitle: art.category, description: "opis"}, user: art.user}
        console.log(jsonToSend);

        this.http.post('http://localhost:8084/articles/articles/addArticle', jsonToSend).subscribe((response: any) => {
              console.log(jsonToSend);
              this.router.navigate(['/articles'])
            });
          
        }

        public getArticle(id: number){
            return this.http.get('http://localhost:8084/articles/articles/getById?articleId='+id)
              .pipe(map((response: any) => response));
          }
    }
