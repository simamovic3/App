import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup,FormBuilder,Validators} from '@angular/forms';
//import {ValidationService} from '../../services/validation.service';
import {Router} from "@angular/router";
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit{

  username = '';
  password = '';
  errorMessage = null;
  public loginForm:FormGroup;

  constructor(protected fb: FormBuilder, protected loginService: LoginService) {
    this.loginForm = this.fb.group({
      'username': [this.username, Validators.required],
      'password': [this.password, Validators.required]
    });
  }

  ngOnInit() {

    localStorage.removeItem('auth_token');
    localStorage.removeItem('logged_user');
    localStorage.removeItem('role');

  }

  login(post) {

   if(!this.loginService.loginUser(post)){
     this.errorMessage= 'Wrong email or password.'
   }
   this.loginForm.reset();
  }
}