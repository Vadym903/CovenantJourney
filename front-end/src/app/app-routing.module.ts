import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapComponent } from "./components/map/map.component";
import { HomeComponent } from "./components/home/home.component";

const routes: Routes = [
  // {path: '', component: MapComponent}
  {path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
