import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {ClientService} from "./client.service";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public client: Client = new Client();
  public title: string = "Create client";

  constructor(private clientService: ClientService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  public create(): void {
    this.clientService.create(this.client).subscribe(
      response => {
        this.router.navigate(['/clients']);
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Client was created',
          showConfirmButton: false,
          timer: 2500
        })
      }
    )
  }

}
