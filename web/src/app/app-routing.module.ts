import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HaendlerLoginComponent } from 'src/app/haendler-login/haendler-login.component';
import { LieferantenMenuComponent } from './lieferanten-menu/lieferanten-menu.component';
import { HaendlerMenuComponent } from './haendler-menu/haendler-menu.component';
import { LieferplanComponent } from './lieferplan/lieferplan.component';
import { HomeComponent } from './home/home.component';
import { HaendlerInventarComponent } from './haendler-inventar/haendler-inventar.component';
import { HaendlerVerkaufComponent } from './haendler-verkauf/haendler-verkauf.component';
import { VerwaltungMenuComponent } from './verwaltung-menu/verwaltung-menu.component';
import { NeuesProduktComponent } from './neues-produkt/neues-produkt.component';
import { NeueVerkaufsstelleComponent } from './neue-verkaufsstelle/neue-verkaufsstelle.component';


const routes: Routes = [
  { path: "app-haendler-login", component: HaendlerLoginComponent },
  { path: "app-lieferanten-menu", component: LieferantenMenuComponent },
  { path: "app-haendler-menu/:name", component: HaendlerMenuComponent },
  { path: "app-lieferplan", component: LieferplanComponent },
  { path: "app-home", component: HomeComponent },
  { path: "", redirectTo: "app-home", pathMatch: "full" },
  { path: "app-haendler-inventar/:name", component: HaendlerInventarComponent },
  { path: "app-haendler-verkauf/:name", component: HaendlerVerkaufComponent },
  { path: "app-verwaltung-menu", component: VerwaltungMenuComponent },
  { path: "app-neues-produkt", component: NeuesProduktComponent },
  { path: "app-neue-verkaufsstelle", component: NeueVerkaufsstelleComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
