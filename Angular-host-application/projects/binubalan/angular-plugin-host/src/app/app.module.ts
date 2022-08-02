import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AngularPluginInterfacesModule } from '@binubalan/angular-plugin-interfaces';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserIdentityModule } from './userIdentity/userIdentity.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularPluginInterfacesModule,
    UserIdentityModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
