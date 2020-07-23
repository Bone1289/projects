import {Component, OnInit} from '@angular/core';
import {AuthService} from "../users/auth.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, public route: Router) {
  }

  ngOnInit(): void {
  }

  logout(): void {
    let username = `${this.authService.user.username}`;
    this.authService.logout();
    Swal.fire('Login', `Logout ${username} successfully!`, 'info');
    this.route.navigate(['/login']);
  }
}
