import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./components/home/home.component";
import { ApartmentsComponent } from "./components/my-apartments/apartments.component";
import { AllApartmentsComponent } from "./components/all-apartments/all-apartments.component";
import { ApartmentInfoComponent } from "./components/apartment-info/apartment-info.component";
import { UserInfoComponent } from "./components/user-info/user-info.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'my-apartments', component: ApartmentsComponent},
    {path: 'info/:id', component: ApartmentInfoComponent},
    {path: 'user/:id', component: UserInfoComponent},
    {path: 'all-apartments', component: AllApartmentsComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
