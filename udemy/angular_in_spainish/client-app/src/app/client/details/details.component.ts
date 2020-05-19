import {Component, Input, OnInit} from '@angular/core';
import {ClientService} from "../client.service";
import {ActivatedRoute} from "@angular/router";
import {Client} from "../client";
import Swal from "sweetalert2";
import {HttpEventType} from "@angular/common/http";
import {ModalService} from "./modal.service";

@Component({
  selector: 'client-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  @Input() client: Client;
  title = "Client Details";
  selectedPhoto: File;
  progress: number = 0;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute,
              public modalService: ModalService
  ) {
  }

  ngOnInit()
    :
    void {
    this.progress = 0;
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
    this.progress = 0;
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
      .subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round((event.loaded / event.total) * 100);
          } else if (event.type === HttpEventType.Response) {
            let response: any = event.body;
            this.client = response.client as Client;
            this.modalService.notificationUpload.emit(this.client);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: "File uploaded.",
              showConfirmButton: false,
              timer: 2500
            });
          }
        }
      );
  }

  closeModal() {
    this.modalService.closeModal();
    this.selectedPhoto = null;
    this.progress = 0;
  }
}
