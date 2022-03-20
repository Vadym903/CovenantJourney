import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { MarkerCardComponent } from './components/marker-card/marker-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MarkerCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LeafletModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
