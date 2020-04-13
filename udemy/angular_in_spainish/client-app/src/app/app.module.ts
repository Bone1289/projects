import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import { FooterComponent } from './footer/footer.component';
import { ClientComponent } from './client/client.component';

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, ClientComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
