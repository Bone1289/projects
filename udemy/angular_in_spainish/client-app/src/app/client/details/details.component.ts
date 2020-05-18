import {Component, OnInit} from '@angular/core';
import {ClientService} from "../client.service";
import {ActivatedRoute} from "@angular/router";
import {Client} from "../client";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  client: Client;
  title = "Client Details";
  selectedPhoto: File;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id: number = +params.get("id")
      if (id) {
        this.clientService.getClient(id).subscribe(client => {
          this.client = client;
        });
      }
    });
  }

  selectPhoto(event) {
    this.selectedPhoto = event.target.files[0];
    console.log(this.selectedPhoto);
    if (this.selectedPhoto.type.indexOf('image') < 0) {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: "File should be an image",
        showConfirmButton: false,
        timer: 2500
      });
      this.selectedPhoto = null;
    }
  }

  uploadPhoto() {
    this.clientService
      .uploadPhoto(this.selectedPhoto, this.client.id)
      .subscribe(client => {
        this.client = client;
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: "File uploaded.",
          showConfirmButton: false,
          timer: 2500
        });
        this.selectedPhoto=null;
      });
  }
}
