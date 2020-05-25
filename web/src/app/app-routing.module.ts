import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HaendlerLoginComponent } from 'src/app/haendler-login/haendler-login.component';
import { LieferantenLoginComponent } from 'src/app/lieferanten-login/lieferanten-login.component';
import { LieferantenMenuComponent } from './lieferanten-menu/lieferanten-menu.component';
import { HaendlerMenuComponent } from './haendler-menu/haendler-menu.component';
import { LieferplanComponent } from './lieferplan/lieferplan.component';


const routes: Routes = [
  { path: "app-haendler-login", component: HaendlerLoginComponent },
  { path: "app-lieferanten-login", component: LieferantenLoginComponent },
  { path: "app-lieferanten-menu", component: LieferantenMenuComponent },
  { path: "app-haendler-menu", component: HaendlerMenuComponent },
  { path: "app-lieferplan", component: LieferplanComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
