import {Injectable} from '@angular/core';
import {User} from "./user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _user: User;
  private _token: string;

  private static USER_STORAGE_KEY: string = "user";
  private static TOKEN_STORAGE_KEY: string = "token";
  private static URL_TOKEN: string = 'http://localhost:8080/oauth/token';

  constructor(private http: HttpClient) {
  }

  public get user(): User {
    if (this._user != null) {
      return this._user;
    } else if (this._user == null && sessionStorage.getItem(AuthService.USER_STORAGE_KEY) != null) {
      this._user = JSON.parse(sessionStorage.getItem(AuthService.USER_STORAGE_KEY)) as User;
      return this._user;
    }
    return new User();
  }

  public get token(): string {
    if (this._token != null) {
      return this._token;
    } else if (this._token == null && sessionStorage.getItem(AuthService.TOKEN_STORAGE_KEY) != null) {
      this._token = sessionStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
      return this._token;
    }
    return null;
  }

  login(user: User): Observable<any> {
    const credential = btoa("angularapp" + ":" + 'testtest');

    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + credential
    });

    let params = new URLSearchParams();
    params.set("grant_type", "password");
    params.set("username", user.username);
    params.set("password", user.password);
    return this.http.post<any>(AuthService.URL_TOKEN, params.toString(), {headers: httpHeaders});
  }

  logout(): void {
    this._token = null;
    this._user = null;
    sessionStorage.clear();
    sessionStorage.removeItem(AuthService.TOKEN_STORAGE_KEY);
    sessionStorage.removeItem(AuthService.USER_STORAGE_KEY);
  }

  setUser(accessToken: string): void {
    let payload = this.getDataToken(accessToken);
    this._user = new User();
    this._user.firstName = payload.firstName;
    this._user.lastName = payload.lastName;
    this._user.email = payload.email;
    this._user.username = payload.user_name;
    this._user.roles = payload.authorities;
    sessionStorage.setItem(AuthService.USER_STORAGE_KEY, JSON.stringify(this._user));
  }

  setToken(accessToken: string): void {
    this._token = accessToken;
    sessionStorage.setItem(AuthService.TOKEN_STORAGE_KEY, accessToken);
  }

  getDataToken(accessToken: string): any {
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }

  isAuthenticated(): boolean {
    let payload = this.getDataToken(this.token);
    return payload!=null && payload.user_name && payload.user_name.length > 0;
  }

  hasRole(role: string): boolean {
    return this.user.roles.includes(role);
  }


}
