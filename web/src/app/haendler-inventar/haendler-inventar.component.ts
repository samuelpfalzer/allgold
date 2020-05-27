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
  sub: any;
  inventar: InventarRow[];
  columns: string[] = ["Produkt", "Vorrat", "Bedarf"];
  neuesProdukt: string = "";
  produkte: string[] = ["Pimmelkäse", "Eichelkäse"];

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sub = this.route.paramMap.subscribe((p) => {
      this.name = p.get("name");
      this.refreshInventar();
    });
  }

  refreshInventar() {
    this.inventar = null;
    this.http.get("/api/inventar", { params: { verkaufsstelle: this.name } }).subscribe(
      (elements: InventarRow[]) => {
        this.inventar = elements;
        console.log(this.inventar);
      }
    );
  }

  /* TODO: Bei Bedarfsänderung PUT-Request senden + API dazu implementieren! */

  wenigerBedarf(element: any) {
    this.inventar = this.inventar.map(x => (x.produkt === element.produkt) ? { produkt: x.produkt, vorrat: x.vorrat, bedarf: (x.bedarf > 0) ? (x.bedarf - 1) : x.bedarf } : x);
  }

  mehrBedarf(element: any) {
    this.inventar = this.inventar.map(x => (x.produkt === element.produkt) ? {
      produkt: x.produkt, vorrat: x.vorrat, bedarf: Number(x.bedarf) + 1
    } : x);
  }

  produktAnfordern() {
    this.inventar.push({ produkt: this.neuesProdukt, vorrat: 0, bedarf: 1 });
    this.inventar = this.inventar.map(x => x); /* if removed, table will not update */
    this.neuesProdukt = "";
    console.log(this.inventar);
  }

}


export interface InventarRow {
  produkt: string;
  vorrat: number;
  bedarf: number;
}
