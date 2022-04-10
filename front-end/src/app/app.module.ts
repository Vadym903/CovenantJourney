import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapComponent } from './components/map/map.component';

import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { MarkerCardComponent } from './components/map/marker-card/marker-card.component';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from "./modules/matherial.module";
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { ApartmentsComponent } from './components/apartments/apartments.component';
import { ModifyApartmentDialogComponent } from "./components/apartments/modify-apartment-dialog/modify-apartment-dialog.component";

@NgModule({
    declarations: [
        AppComponent,
        MapComponent,
        MarkerCardComponent,
        HomeComponent,
        NavBarComponent,
        LoginComponent,
        ApartmentsComponent,
        ModifyApartmentDialogComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        LeafletModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MaterialModule
    ],
    providers: [HttpClientModule, CookieService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
