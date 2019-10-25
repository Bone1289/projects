import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from "./auth.service";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BikeService {
  private static endpoint = '/server/api/v1/bikes';

  constructor(private http: HttpClient, private auth: AuthService) {

  }

  getBikes() {
    // const token = localStorage.getItem('access_token');
    // const token = this.auth.getTokenSilently$();
    // console.log(token);
    return this.http.get(BikeService.endpoint, /*{
      headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)
    }*/);
  }

  getBike(id: number) {
    // const token = localStorage.getItem('access_token');
    // const token = this.auth.getTokenSilently$();
    return this.http.get(BikeService.endpoint + '/' + id,
      /*{
        headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)
      }*/);
  }

  createBikeRegistration(bike) {
    const body = JSON.stringify(bike);
    return this.http.post(BikeService.endpoint, body, httpOptions);
  }
}
