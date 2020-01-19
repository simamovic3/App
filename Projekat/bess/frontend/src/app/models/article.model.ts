import { UserArticle } from "./user-article.model";
import { Category } from "./category.model";

export class Article{

    public articleId : number;
    public title: string;
    public description: string;
    public category: Category;
    public user: UserArticle;
    public pictureUrl: string;

    constructor(articleId:number, title:string, description:string, category: Category, user: UserArticle, url: string)
    {
         this.articleId = this.articleId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.pictureUrl = url;
    }

 
}