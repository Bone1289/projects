import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {ClientService} from "./client.service";
import {Router, ActivatedRoute} from "@angular/router";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public client: Client = new Client();
  public title: string = "Create client";

  constructor(private clientService: ClientService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loadClient();
  }

  public loadClient(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.clientService.getClient(id).subscribe(client => this.client = client)
      }
    });
  }

  public create(): void {
    this.clientService.create(this.client).subscribe(
      clientResponse => {
        this.router.navigate(['/clients']);
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: clientResponse.message,
          showConfirmButton: false,
          timer: 2500
        })
      }
    )
  }

  public update(): void {
    this.clientService.update(this.client).subscribe(clientResponse => {
      this.router.navigate(['/clients']);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: `${clientResponse.message}: ${clientResponse.client.firstName}`,
        showConfirmButton: false,
        timer: 2500
      })
    })
  }

}
