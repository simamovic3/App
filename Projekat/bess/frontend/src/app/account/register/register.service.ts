import { FormGroup, Form,AbstractControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { constructDependencies } from '@angular/core/src/di/reflective_provider';

@Injectable()
export class RegisterService {
    resp : boolean = false;
    respRegister: boolean = false;
    duplicateEmail:boolean = false;
    projects : any;
    constructor (private http: HttpClient, private router:Router){}

	public registerUser(post): boolean{    
        
        this.http.post('http://localhost:8092/users/user/add',{
            name : post.name,
            lastName : post.lastName,
            email: post.email,
            username : post.username,
            password : post.password,
            age : post.age,
            role: 1
        })
          .subscribe((response: any) => {
              console.log(response);
              this.resp = true;

              this.router.navigate(['/login'])
            },
          (err: any) => {console.error(err);});
        return this.resp;

    }
}