import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {HttpClientModule} from "@angular/common/http";
import { QuestionComponent } from './forum/question/question.component';
import { MyHttpService } from './services/http.service';
import { ReactiveFormsModule } from '@angular/forms';
import { QuestionAddComponent } from './forum/question/question-add/question-add.component'
import { HomeComponent } from './components/home/home.component';
import { NavMenuComponent } from './components/nav-menu/nav-menu.component';
import { LoginComponent } from './account/login/login.component';
import { RegisterComponent } from './account/register/register.component';
import { LoginService } from './account/login/login.service';
import { RegisterService } from './account/register/register.service';
import { AuthInterceptor } from './services/auth-interceptor.service';
import {Http, Headers, ConnectionBackend, HttpModule} from '@angular/http';
import { QuestionListComponent } from './forum/question/question-list/question-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { ArticleListComponent } from './article/article/article-list/article-list.component';
import { ArticleComponent } from 'src/app/article/article/article.component';
import { ArticleService } from './article/article/article.service';
import { ShowArticleComponent } from './article/article/show-article/show-article.component';
import { AdminListUsersComponent } from './account/admin-list-users/admin-list-users.component';
import { GlobalApp } from './global';
import { UserProfileComponent } from './account/user-profile/user-profile.component';



@NgModule({
  declarations: [
    AppComponent,
    QuestionComponent,
    HomeComponent,
    NavMenuComponent,
    QuestionAddComponent,
    LoginComponent,
    RegisterComponent,
    QuestionListComponent,
    FooterComponent,
    ArticleComponent,
    ArticleListComponent,
    ShowArticleComponent,
    AdminListUsersComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule

  ],
  providers: [MyHttpService, RegisterService, LoginService, AuthInterceptor, ArticleService, GlobalApp],
  bootstrap: [AppComponent]
})
export class AppModule { }
