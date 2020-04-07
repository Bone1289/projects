import { Component, OnInit } from '@angular/core';
import { SalesPerson } from './sales-person';

@Component({
  selector: 'app-sales-person-list',
  templateUrl: './sales-person-list.component.html',
  styleUrls: ['./sales-person-list.component.css']
})
export class SalesPersonListComponent implements OnInit {

  salesPersonList: SalesPerson[] = [
    new SalesPerson('Test 1', 'Test 2', 'test@test.com', 100),
    new SalesPerson('User 1', 'User 2', 'user@user.com', 200),
    new SalesPerson('Person 1', 'Person 2', 'person@person.com', 300),
    new SalesPerson('Personas 1', 'Personas 2', 'personas@personas.com', 400)
  ];

  constructor() { }

  ngOnInit() {
  }

}
