import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
/* import { FlexLayoutModule } from '@angular/flex-layout';
 */
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { HaendlerLoginComponent } from './haendler-login/haendler-login.component';
import { LieferantenLoginComponent } from './lieferanten-login/lieferanten-login.component';
import { LieferantenMenuComponent } from './lieferanten-menu/lieferanten-menu.component';
import { HaendlerMenuComponent } from './haendler-menu/haendler-menu.component';
import { LieferplanComponent } from './lieferplan/lieferplan.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { HomeComponent } from './home/home.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HaendlerInventarComponent } from './haendler-inventar/haendler-inventar.component';
import { HaendlerVerkaufComponent } from './haendler-verkauf/haendler-verkauf.component';
import { VerwaltungMenuComponent } from './verwaltung-menu/verwaltung-menu.component';
import { NeuesProduktComponent } from './neues-produkt/neues-produkt.component';
import { NeueVerkaufsstelleComponent } from './neue-verkaufsstelle/neue-verkaufsstelle.component';


@NgModule({
  declarations: [
    AppComponent,
    HaendlerLoginComponent,
    LieferantenLoginComponent,
    LieferantenMenuComponent,
    HaendlerMenuComponent,
    LieferplanComponent,
    HomeComponent,
    HaendlerInventarComponent,
    HaendlerVerkaufComponent,
    VerwaltungMenuComponent,
    NeuesProduktComponent,
    NeueVerkaufsstelleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    /* FlexLayoutModule, */
    BrowserAnimationsModule,
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatCardModule,
    MatDividerModule,
    MatIconModule,
    MatSelectModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
