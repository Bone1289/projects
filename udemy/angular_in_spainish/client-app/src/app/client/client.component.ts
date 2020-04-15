import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {CLIENTES} from "./clients.json";
import {ClientService} from "./client.service";

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

}
