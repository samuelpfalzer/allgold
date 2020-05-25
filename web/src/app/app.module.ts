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


@NgModule({
  declarations: [
    AppComponent,
    HaendlerLoginComponent,
    LieferantenLoginComponent,
    LieferantenMenuComponent,
    HaendlerMenuComponent,
    LieferplanComponent
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
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
