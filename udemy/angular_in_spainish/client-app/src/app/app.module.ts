import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from './footer/footer.component';
import {ClientComponent} from './client/client.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {FormComponent} from './client/form.component';
import {FormsModule} from "@angular/forms";
import {PaginatorComponent} from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatMomentDateModule} from "@angular/material-moment-adapter";

const routes: Routes = [
  {path: '', redirectTo: '/clients', pathMatch: 'full'},
  {path: 'clients', component: ClientComponent},
  {path: 'clients/page/:page', component: ClientComponent},
  {path: 'clients/form', component: FormComponent},
  {path: 'clients/form/:id', component: FormComponent}
]

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, ClientComponent, FormComponent, PaginatorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMomentDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
