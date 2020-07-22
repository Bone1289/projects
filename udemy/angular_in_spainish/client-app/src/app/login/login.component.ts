import { Component, OnInit } from '@angular/core';
import {User} from "../users/user";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  title:string = 'Please login';
  user: User;

  constructor() {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  login():void{
    console.log(this.user);

    if(this.user.username == null || this.user.password ==null){
      Swal.fire('Error Login', "Username and password are not correct", 'error');
    }
  }
}
