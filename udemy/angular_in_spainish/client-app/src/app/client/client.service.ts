import {Injectable} from '@angular/core';
import {CLIENTES} from "./clients.json";
import {Client} from "./client";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor() {
  }

  getClients(): Observable<Client[]> {
    return of(CLIENTES);
  }
}