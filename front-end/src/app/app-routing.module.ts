import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./components/home/home.component";
import { ApartmentsComponent } from "./components/my-apartments/apartments.component";
import { AllApartmentsComponent } from "./components/all-apartments/all-apartments.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'my-my-apartments', component: ApartmentsComponent},
    {path: 'all-my-apartments', component: AllApartmentsComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
