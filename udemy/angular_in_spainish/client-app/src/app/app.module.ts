import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from './footer/footer.component';
import {ClientComponent} from './client/client.component';
import {RouterModule, Routes} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormComponent} from './client/form.component';
import {FormsModule} from "@angular/forms";
import {PaginatorComponent} from './paginator/paginator.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import {registerLocaleData} from "@angular/common";
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter'
import localUs from '@angular/common/locales/en-GB';
import {DetailsComponent} from './client/details/details.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from "./users/guards/auth.guard";
import {RoleGuard} from "./users/guards/role.guard";
import {TokenInterceptor} from "./users/interceptor/token.interceptor";
import {AuthInterceptor} from "./users/interceptor/auth.interceptor";
import {DetailsInvoiceComponent} from "./invoices/details-invoice.component";

registerLocaleData(localUs, 'en')

const routes: Routes = [
  {path: '', redirectTo: '/clients', pathMatch: 'full'},
  {path: 'clients', component: ClientComponent},
  {path: 'clients/page/:page', component: ClientComponent},
  {path: 'clients/form', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
  {path: 'clients/form/:id', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ROLE_ADMIN'}},
  {path: 'clients/view/:id', component: DetailsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'invoice/:id', component: DetailsInvoiceComponent}
];

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent,
    ClientComponent, FormComponent, PaginatorComponent,
    DetailsComponent, LoginComponent, DetailsInvoiceComponent
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
  providers: [
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}},
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
