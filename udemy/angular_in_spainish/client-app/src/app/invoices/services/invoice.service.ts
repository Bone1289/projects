import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Invoice} from "../models/invoice";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private urlEndpoint: string = "http://localhost:8080/api";
  private urlInvoiceEndpoint: string = this.urlEndpoint + "/invoices";

  constructor(private http: HttpClient) {
  }

  getInvoice(id: number): Observable<Invoice> {
    return this.http.get<Invoice>(`${this.urlInvoiceEndpoint}/${id}`);
  }
}
