import {Injectable} from '@angular/core';
import {CLIENTES} from "./clients.json";
import {Client} from "./client";
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private urlEndpoint:string = "http://localhost:8080/api";
  private urlClientEndpoint:string= this.urlEndpoint+"/clients";

  constructor(private http:HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.urlClientEndpoint)
  }
}
