import {Component, OnInit} from '@angular/core';
import {User} from "../users/user";
import Swal from 'sweetalert2';
import {AuthService} from "../users/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  title: string = 'Please login';
  user: User;

  constructor(private authService: AuthService,
              private router: Router) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  login(): void {
    console.log(this.user);

    if (this.user.username == null || this.user.password == null) {
      Swal.fire('Error Login', "Username and password are not correct", 'error');
      return;
    }

    this.authService.login(this.user).subscribe(response =>{
      let accessToken = response.access_token;
      // let payload = JSON.parse(atob(accessToken.split(".")[1]));
      this.authService.setUser(accessToken);
      this.authService.setToken(accessToken);

      let user = this.authService.user;

      this.router.navigate(['/clients']);

      Swal.fire('Login', `Hello ${user.username}!`, 'success');
    }, error => {
      if(error.status == 400){
        Swal.fire('Error Login', "Username or password are not correct", 'error');
      }
    });
  }
}
