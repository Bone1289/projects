import {Component, Input, OnInit, OnChanges, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit, OnChanges {

  @Input() pagination: any;

  pages: number[];

  since: number;
  to: number;

  constructor() {
  }

  ngOnInit(): void {
    this.initPagination();
  }

  ngOnChanges(changes: SimpleChanges) {
    let paginationUpdate = changes['pagination']
    if (paginationUpdate.previousValue) {
      this.initPagination();
    }
  }

  private initPagination() {
    this.since = Math.min(Math.max(1, this.pagination.number - 1), this.pagination.totalPages - 4);
    this.to = Math.max(Math.min(this.pagination.totalPages, this.pagination.number + 3), 5);

    if (this.pagination.totalPages > 5) {
      this.pages = new Array(this.to - this.since + 1).fill(0).map((_value, index) => index + this.since);
    } else {
      this.pages = new Array(this.pagination.totalPages).fill(0).map((_value, index) => index + 1);
    }
  }
}
