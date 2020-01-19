import { FormGroup, Form,AbstractControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { constructDependencies } from '@angular/core/src/di/reflective_provider';
import { AuthInterceptor } from '../../services/auth-interceptor.service';
import { User } from '../../models/user';

@Injectable()
export class LoginService {

    resp : boolean = false;
    respRegister: boolean = false;
    duplicateEmail:boolean = false;
    projects : any;
    constructor (private http: HttpClient, private router:Router, private interceptor: AuthInterceptor){}

    public loginUser(user): boolean{    
        
        console.log('Servis '+ user.username);  

        let jsonToSend = {username: user.username, password: user.password}

        this.http.post('http://localhost:8092/users/login', jsonToSend)

          .subscribe((response: any) => {

            localStorage.setItem('auth_token', response.token);
            localStorage.setItem('logged_user', jsonToSend.username);

            console.log(response);     
            this.resp = true;    
                
            this.interceptor.get('http://localhost:8092/users/getUserProfile')
            .subscribe(data => {

            
            console.log(data.json());

            localStorage.setItem('role', data.json().role);
        
             this.router.navigate(['/'])
             });
         },

          (err: any) => {console.error(err);});

        return this.resp;

    }
}