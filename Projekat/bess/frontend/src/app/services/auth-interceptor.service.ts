import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import { GlobalApp } from '../global';

@Injectable()
export class AuthInterceptor {

  constructor(private http: Http, private app: GlobalApp) {}

  createAuthorizationHeader(headers: Headers) {
    headers.append('Token', this.app.localStorageItem('auth_token')); 
  }

  get(url) {
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.get(url, {
      headers: headers
    });
  }

  post(url, data) {
    let headers = new Headers();
    this.createAuthorizationHeader(headers);
    return this.http.post(url, data, {
      headers: headers
    });
  }
}