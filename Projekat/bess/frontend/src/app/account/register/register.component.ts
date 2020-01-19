import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-registration',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent  {

  username = '';
  password = '';
  name = '';
  lastName = '';
  email = '';
  age = '';
  errorMessage = null;
  public registrationForm:FormGroup;

  constructor(protected fb: FormBuilder, protected registrationService: RegisterService) {
    this.registrationForm = this.fb.group({
      'name': [this.name, Validators.required],
      'lastName': [this.lastName, Validators.required],
      'email': [this.email, Validators.required],
      'username': [this.username, Validators.required],
      'password': [this.password, Validators.required],
      'age': [this.age, Validators.required]
    });
  }

  register(post) {
   if(!this.registrationService.registerUser(post)){
     this.errorMessage= 'Registration failed';
   }
   this.registrationForm.reset();
  }
}