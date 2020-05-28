import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-haendler-verkauf',
  templateUrl: './haendler-verkauf.component.html',
  styleUrls: ['./haendler-verkauf.component.css']
})
export class HaendlerVerkaufComponent implements OnInit {
  name: string;
  verkauf: VerkaufsPosten[] = [];
  inventar: VerkaufsPosten[] = [];
  columns: string[] = ["Produkt", "Menge", "Preis"];
  neuesProdukt: string = null;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((p) => {
      this.name = p.get("name");
      this.http.get("/api/inventar", { params: { verkaufsstelle: this.name } }).subscribe(
        (x: any[]) => {
          this.inventar = x.map(a => <VerkaufsPosten>{ artnr: a.artnr, produkt: a.produkt, vorrat: a.vorrat, preis: a.preis, menge: 0 }).filter(b => b.vorrat > 0);
          console.log(this.inventar);
        }
      )
    });
  }

  neuerPosten() {
    this.verkauf.push(this.inventar.find(x => x.produkt == this.neuesProdukt));
    this.verkauf = this.verkauf.map(a => {
      let b = a;
      if (b.produkt == this.neuesProdukt) {
        b.menge = 1;
        return b;
      } else {
        return a;
      }
    });
    this.inventar = this.inventar.filter(x => (x.produkt != this.neuesProdukt) ? x : null);
    this.inventar = this.inventar.map(a => a);
    this.neuesProdukt = null;
  }

  getGesamtpreis(): number {
    return this.verkauf.reduce((a, b) => a + (b.menge * b.preis), 0);
  }

  mehrVon(element: VerkaufsPosten) {
    this.verkauf = this.verkauf.map((a) => (element === a) ? { artnr: a.artnr, produkt: a.produkt, preis: a.preis, menge: Number(a.menge) + 1, vorrat: a.vorrat } : a);
  }

  wenigerVon(element: VerkaufsPosten) {
    this.verkauf = this.verkauf.map((a) => (element === a) ? { artnr: a.artnr, produkt: a.produkt, preis: a.preis, menge: Number(a.menge) - 1, vorrat: a.vorrat } : a);
  }

  verkaufAbbrechen() {
    this.router.navigate(['/app-haendler-menu', this.name]);
  }

  verkaufen() {
    let posten = this.verkauf.map(a => <object>{ id: a.artnr, menge: a.menge });
    this.http.post(
      "/api/verkauf",
      {
        verkaufsstelle: this.name,
        posten: posten
      }
    ).subscribe((response: Response) => {
      console.log("Pimmel " + response.status);
      this.router.navigate(['/app-haendler-menu', this.name]);
    });
  }

}


export interface VerkaufsPosten {
  artnr: number;
  produkt: string;
  vorrat: number;
  preis: number;
  menge: number;
}