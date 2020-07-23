import {Injectable} from '@angular/core';
import {Client} from "./client";
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import {catchError, map, tap} from "rxjs/operators";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {ClientResponse} from "./client.response";
import {Region} from "./region";
import {AuthService} from "../users/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private urlEndpoint: string = "http://localhost:8080/api";
  private urlClientEndpoint: string = this.urlEndpoint + "/clients";

  constructor(private http: HttpClient,
              private router: Router,
              private authService: AuthService) {
  }

  private isAuthorized(e): boolean {
    if (e.status == 401) {
      if (this.authService.isAuthenticated()) {
        this.authService.logout();
      }

      this.router.navigate(['/login']);
      return true;
    }

    if (e.status == 403) {
      Swal.fire("Access denied", `Hello ${this.authService.user.username} you don't have access!`, 'warning');
      this.router.navigate(['/clients']);
      return true;
    }
    return false;
  }

  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(
      this.urlClientEndpoint + "/regions"
    ).pipe(
      catchError(e => {
        this.isAuthorized(e);
        return throwError(e);
      })
    );
  }

  getClients(page): Observable<any> {
    return this.http.get(this.urlClientEndpoint + "/page/" + page,).pipe(
      map((response: any) => {
        (response.content as Client[]).map(client => {
          client.firstName = client.firstName.toUpperCase();
          return client;
        });
        return response;
      })
    );

  }

  getClient(id): Observable<Client> {
    return this.http.get<Client>(`${this.urlClientEndpoint}/${id}`).pipe(
      catchError(e => {
        if (this.isAuthorized(e)) {
          return throwError(e);
        }

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
    return this.http.post<ClientResponse>(this.urlClientEndpoint, client).pipe(
      catchError(e => {
        if (this.isAuthorized(e)) {
          return throwError(e);
        }

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
    return this.http.put<ClientResponse>(`${this.urlClientEndpoint}/${client.id}`, client).pipe(
      catchError(e => {
        if (this.isAuthorized(e)) {
          return throwError(e);
        }

        console.error(e.error.message);
        Swal.fire(e.error.message, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  deleteClient(id): Observable<ClientResponse> {
    return this.http.delete<ClientResponse>(`${this.urlClientEndpoint}/${id}`).pipe(
      catchError(e => {
        if (this.isAuthorized(e)) {
          return throwError(e);
        }

        console.error(e.error.message);
        Swal.fire(e.error.message, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  uploadPhoto(fileInfo: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("file", fileInfo);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.urlClientEndpoint}/uploads`, formData, {
      reportProgress: true
    });

    return this.http.request(req).pipe(
      catchError(e => {
        this.isAuthorized(e);
        return throwError(e);
      })
    );
  }
}
