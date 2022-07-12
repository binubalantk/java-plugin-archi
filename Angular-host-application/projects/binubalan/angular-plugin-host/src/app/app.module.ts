import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AngularPluginInterfacesModule } from '@binubalan/angular-plugin-interfaces';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularPluginInterfacesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
