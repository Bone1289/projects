import {Injectable} from '@angular/core';
import {CLIENTES} from "./clients.json";
import {Client} from "./client";
import {Observable, of} from "rxjs";
import {HttpClient, HttpHandler, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private urlEndpoint: string = "http://localhost:8080/api";
  private urlClientEndpoint: string = this.urlEndpoint + "/clients";
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.urlClientEndpoint)
  }

  getClient(id): Observable<Client> {
    return this.http.get<Client>(`${this.urlClientEndpoint}/${id}`);
  }

  create(client: Client): Observable<Client> {
    return this.http.post<Client>(this.urlClientEndpoint, client, {headers: this.httpHeaders});
  }

  update(client: Client): Observable<Client> {
    return this.http.put<Client>(`${this.urlClientEndpoint}/${client.id}`, client, {headers: this.httpHeaders})
  }
}
