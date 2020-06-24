import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-haendler-inventar',
  templateUrl: './haendler-inventar.component.html',
  styleUrls: ['./haendler-inventar.component.css']
})
export class HaendlerInventarComponent implements OnInit {
  name: string;
  inventar: InventarPosten[];
  bestellbar: ProduktPosten[];
  columns: string[] = ["Artikelnummer", "Produkt", "Vorrat", "Bedarf"];
  neuesProdukt: string = null;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((p) => {
      this.name = p.get("name");
      this.refreshInventar();
    });
  }

  refreshInventar() {
    this.http.get("/api/inventar", { params: { verkaufsstelle: this.name } }).subscribe(
      (elements: InventarPosten[]) => {
        this.inventar = elements;
        if (this.inventar == null) {
          this.inventar = [];
        }
        this.refreshProdukte();
      }
    );
  }

  refreshProdukte() {
    this.http.get("/api/produkt").subscribe(
      (elements: ProduktPosten[]) => {
        this.bestellbar = elements.filter(a => this.inventar.filter(b => b.artnr === a.id).length === 0);
        if (this.bestellbar == null) {
          this.bestellbar = [];
        }
      }
    );
  }

  /* TODO: Bei Bedarfsänderung PUT-Request senden + API dazu implementieren! */

  wenigerBedarf(element: any) {
    this.inventar = this.inventar.map(x => (x.produkt === element.produkt) ? { artnr: x.artnr, produkt: x.produkt, vorrat: x.vorrat, bedarf: (x.bedarf > 0) ? (x.bedarf - 1) : x.bedarf } : x);
    this.http.put("/api/inventar", { verkaufsstelle: this.name, artnr: element.artnr, bedarf: element.bedarf - 1 }).subscribe(response => {
      console.log("pimmel");
    });
  }

  mehrBedarf(element: any) {
    this.inventar = this.inventar.map(x => (x.produkt === element.produkt) ? {
      artnr: x.artnr, produkt: x.produkt, vorrat: x.vorrat, bedarf: Number(x.bedarf) + 1
    } : x);
    this.http.put("/api/inventar", { verkaufsstelle: this.name, artnr: element.artnr, bedarf: element.bedarf + 1 }).subscribe(response => {
      console.log("pimmel");
    });
  }

  produktAnfordern() {
    let artnr = (this.bestellbar.filter(e => e.name === this.neuesProdukt))[0].id;
    this.inventar.push({ artnr: artnr, produkt: this.neuesProdukt, vorrat: 0, bedarf: 1 });
    this.inventar = this.inventar.map(x => x); /* if removed, table will not update */
    this.bestellbar = this.bestellbar.filter(element => element.name != this.neuesProdukt);
    this.neuesProdukt = null;
    this.http.post("/api/inventar", { verkaufsstelle: this.name, artnr: artnr }).subscribe(response => { console.log("pimmel") });
  }
}


export interface InventarPosten {
  artnr: number;
  produkt: string;
  vorrat: number;
  bedarf: number;
}


export interface ProduktPosten {
  id: number;
  name: string;
  preis: number;
  bestand: number;
  aktiv; boolean;
}
