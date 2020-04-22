import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {CLIENTES} from "./clients.json";
import {ClientService} from "./client.service";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  clients: Client[];

  constructor(private clientService: ClientService) {
  }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(clients => {
      this.clients = clients
    })
  }

  delete(client: Client): void {
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete ${client.firstName} ${client.lastName}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, don\'t delete'
    }).then((result) => {
      if (result.value) {
        this.clientService.deleteClient(client.id).subscribe(response => {
          this.clients = this.clients.filter(cli => cli.id !== client.id);
          Swal.fire(
            'Deleted!',
            `Client ${client.firstName} ${client.lastName} deleted.`,
            'success'
          )
        });
      }
    })
  }

}
