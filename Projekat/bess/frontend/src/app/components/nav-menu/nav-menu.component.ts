import { Component, OnInit } from '@angular/core';
import { GlobalApp } from '../../global';

@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrls: ['./nav-menu.component.css']
})
export class NavMenuComponent implements OnInit {
  
  isExpanded = false;
  userLoggedIn = false

  constructor(public app: GlobalApp){

  }

  collapse() {
    this.isExpanded = false;
  }

  toggle() {
    this.isExpanded = !this.isExpanded;
  }

  ngOnInit() {

    this.userLoggedIn = localStorage.getItem('auth_token') ? true : false;

  }
}
