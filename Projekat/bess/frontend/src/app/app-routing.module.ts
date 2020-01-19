import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QuestionComponent } from './forum/question/question.component';
import { QuestionAddComponent } from './forum/question/question-add/question-add.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './account/login/login.component';
import { RegisterComponent } from './account/register/register.component';
import { ArticleListComponent } from './article/article/article-list/article-list.component';
import {ArticleComponent} from './article/article/article.component';
import { ShowArticleComponent } from './article/article/show-article/show-article.component';
import { AdminListUsersComponent } from './account/admin-list-users/admin-list-users.component';
import { UserProfileComponent } from './account/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
{ path: 'forum', component: QuestionComponent },
{ path: 'addQuestion', component: QuestionAddComponent },
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent},
{path: 'articles', component: ArticleListComponent},
{ path: 'addArticle', component: ArticleComponent},
{ path: 'showArticle', component: ShowArticleComponent},
{path: 'adminListUsers', component: AdminListUsersComponent},
{ path: 'showArticle/:id', component: ShowArticleComponent},
{ path: 'myProfile', component: UserProfileComponent }


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations:[]
})
export class AppRoutingModule { }
