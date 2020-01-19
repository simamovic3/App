import { Component, OnInit } from '@angular/core';
import { MyHttpService } from '../../services/http.service';
import { User } from '../../models/user';
import swal from 'sweetalert2';
import { AuthInterceptor } from '../../services/auth-interceptor.service';

@Component({
  selector: 'app-admin-list-users',
  templateUrl: './admin-list-users.component.html',
  styleUrls: ['./admin-list-users.component.scss']
})
export class AdminListUsersComponent {

  public users: User[] = new Array<User>();
  
  constructor(protected httpService: MyHttpService, private interceptor: AuthInterceptor) { 
      this.getUsers();
  }

  getUsers(){
    this.httpService.getUsers().subscribe((res:any)=>{
        this.users = res.json() as User[];
        console.log(this.users);
    });
  }

  delete(username: string){

    swal({
        title: 'Are you sure you want to delete user with username: ' + username + '?',
        text: "You will not be able to recover this!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.value) {
            this.httpService.deleteUser(username).subscribe(data => {
              this.users = data.json() as User[];
              console.log(this.users);
            });
        }
    });
  }

  
}
