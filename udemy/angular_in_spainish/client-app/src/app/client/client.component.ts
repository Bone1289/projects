import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {CLIENTES} from "./clients.json";
import {ClientService} from "./client.service";
import Swal from 'sweetalert2';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  clients: Client[];
  pagination: any;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get("page")
      if (!page) {
        page = 0;
      }
      this.clientService.getClients(page).subscribe(response => {
        this.clients = response.content as Client[]
        this.pagination = response
      });
    });


  }

  delete(client: Client): void {
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete ${client.firstName} ${client.lastName}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, don\'t delete'
    }).then((result) => {
      if (result.value) {
        this.clientService.deleteClient(client.id).subscribe(clientResponse => {
          this.clients = this.clients.filter(cli => cli.id !== client.id);
          Swal.fire(
            'Deleted!',
            clientResponse.message,
            'success'
          )
        });
      }
    })
  }

}
