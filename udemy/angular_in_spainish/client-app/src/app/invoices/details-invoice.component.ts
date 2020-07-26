import {Component, OnInit} from '@angular/core';
import {Invoice} from "./models/invoice";
import {InvoiceService} from "./services/invoice.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-details-invoice',
  templateUrl: './details-invoice.component.html'
})
export class DetailsInvoiceComponent implements OnInit {

  invoice: Invoice;
  title: string = 'Invoice';

  constructor(private invoiceService: InvoiceService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = +params.get('id');
      this.invoiceService.getInvoice(id).subscribe(invoice => this.invoice = invoice);
    });
  }

}
