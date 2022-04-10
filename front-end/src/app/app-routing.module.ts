import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./components/home/home.component";
import { ApartmentsComponent } from "./components/apartments/apartments.component";

const routes: Routes = [
    // {path: '', component: MapComponent}
    {path: '', component: HomeComponent},
    {path: 'my-apartments', component: ApartmentsComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
