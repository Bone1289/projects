import {Component, OnInit} from '@angular/core';
import {Client} from "./client";
import {ClientService} from "./client.service";
import {Router, ActivatedRoute} from "@angular/router";
import Swal from 'sweetalert2';
import {Region} from "./region";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public client: Client = new Client();
  public regions: Region[];

  public title: string = "Create client";
  public errors: string[];

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
    this.clientService.getRegions().subscribe(regions => this.regions = regions);
    console.log(this.regions)
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
      },
      err => {
        this.errors = err.error.errors as string[];
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
      },
      err => {
        this.errors = err.error.errors as string[];
      })
  }

  compareRegion(o1: Region, o2: Region): boolean {
    if (o1 === undefined || o2 === undefined) {
      return true;
    }
    return o1 == null || o2 == null ? false : o1.id === o2.id;
  }

}
