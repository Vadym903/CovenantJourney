import { httpInterceptorProviders } from './shared/constants/interceptors';
import { ErrorFormControlDirective } from './_directives/error-form-control-directive';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslationModule } from './translation/translation.module';
import { MaterialModule } from './shared/matherial.module';
import { CookieService } from 'ngx-cookie-service';
import { PhotoUploadComponent } from './photo-upload/photo-upload.component';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { UserPageComponent } from './user-page/user-page.component';
import { UserEditProfilePopupComponent } from './user-page/user-edit-profile-popup/user-edit-profile-popup.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { InvalidLoginPopupComponent } from './invalid-login-popup/invalid-login-popup.component';
import { MainMapComponent } from './components/main-map/main-map.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    RegistrationComponent,
    NavigationBarComponent,
    ErrorFormControlDirective,
    PhotoUploadComponent,
    UserPageComponent,
    UserEditProfilePopupComponent,
    InvalidLoginPopupComponent,
    MainMapComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    TranslationModule,
    MatIconModule,
    MatSelectModule,
    MatDatepickerModule,
    FormsModule,
    MatPaginatorModule,
    LeafletModule
  ],
  providers: [
    CookieService,
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
