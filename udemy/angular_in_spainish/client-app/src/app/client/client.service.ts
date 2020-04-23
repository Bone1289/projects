import {Injectable} from '@angular/core';
import {CLIENTES} from "./clients.json";
import {Client} from "./client";
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {ClientResponse} from "./client.response";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private urlEndpoint: string = "http://localhost:8080/api";
  private urlClientEndpoint: string = this.urlEndpoint + "/clients";
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient,
              private router: Router) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.urlClientEndpoint)
  }

  getClient(id): Observable<Client> {
    return this.http.get<Client>(`${this.urlClientEndpoint}/${id}`).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }

        this.router.navigate(['/clients']);
        console.error(e.error.message);
        Swal.fire("Error on editing", e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  create(client: Client): Observable<ClientResponse> {
    return this.http.post<ClientResponse>(this.urlClientEndpoint, client, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.message);
        Swal.fire(e.error.message, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  update(client: Client): Observable<ClientResponse> {
    return this.http.put<ClientResponse>(`${this.urlClientEndpoint}/${client.id}`, client, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire(e.error.message, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  deleteClient(id): Observable<ClientResponse> {
    return this.http.delete<ClientResponse>(`${this.urlClientEndpoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire(e.error.message, e.error.error, 'error');
        return throwError(e);
      })
    );
  }
}
