import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BikeService {
  private static endpoint = '/server/api/v1/bikes';

  constructor(private http:HttpClient) {

  }

  getBikes() {
    return this.http.get(BikeService.endpoint);
  }

  getBike(id: number) {
    return this.http.get(BikeService.endpoint + '/' + id);
  }

  createBikeRegistration(bike) {
    let body = JSON.stringify(bike);
    return this.http.post(BikeService.endpoint, body, httpOptions);
  }
}
