import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from '../../../models/article.model';
import { ArticleService } from '../article.service';


@Component({
  selector: 'app-show-article',
  templateUrl: './show-article.component.html',
  styleUrls: ['./show-article.component.scss']
})
export class ShowArticleComponent implements OnInit {

  public articleId: number;
  public article: Article = new Article(1,'','',null, null, null);
  public service: ArticleService;

  constructor(private route: ActivatedRoute, articleService: ArticleService) { 
    this.service = articleService;
  }

  ngOnInit() {

    this.route.params.subscribe((params: any) => {
      if(params.id){
        this.articleId = params.id;
        console.log(this.articleId);
      }
    });
    this.service.getArticle(this.articleId).subscribe(data => {
      this.article = data;
      console.log(this.article);
    });
  }

}
