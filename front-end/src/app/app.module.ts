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
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { ApartmentsComponent } from './components/my-apartments/apartments.component';
import { ModifyApartmentDialogComponent } from "./components/my-apartments/modify-apartment-dialog/modify-apartment-dialog.component";
import { ApartmentFormComponent } from './components/my-apartments/modify-apartment-dialog/apartment-form/apartment-form.component';
import { CommonModule } from "@angular/common";
import { ApartmentMapComponent } from './components/my-apartments/modify-apartment-dialog/apartment-map/apartment-map.component';
import { DescriptionFormComponent } from './components/description-form/description-form.component';
import { NgxEditorModule } from "ngx-editor";
import { SingleApartmentCardComponent } from './components/my-apartments/single-apartment-card/single-apartment-card.component';
import { AllApartmentsComponent } from './components/all-apartments/all-apartments.component';
import { SmallApartmentInfoComponent } from './components/all-apartments/small-apartment-info/small-apartment-info.component';
import { ModifyApartmentImagesComponent } from "./components/my-apartments/modify-apartment-dialog/modify-apartment-images/modify-apartment-images.component";
import { AuthInterceptor } from "./_interceptors/auth.interceptor";
import { NgxFileDropModule } from "ngx-file-drop";

@NgModule({
    declarations: [
        AppComponent,
        MapComponent,
        MarkerCardComponent,
        HomeComponent,
        NavBarComponent,
        LoginComponent,
        ApartmentsComponent,
        ModifyApartmentDialogComponent,
        ApartmentFormComponent,
        ApartmentMapComponent,
        DescriptionFormComponent,
        SingleApartmentCardComponent,
        AllApartmentsComponent,
        SmallApartmentInfoComponent,
        ModifyApartmentImagesComponent
    ],
    imports: [
        BrowserModule,
        CommonModule,
        HttpClientModule,
        AppRoutingModule,
        LeafletModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MaterialModule,
        NgxEditorModule,
        NgxFileDropModule
    ],
    providers: [
        HttpClientModule,
        CookieService,
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
