import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-haendler-verkauf',
  templateUrl: './haendler-verkauf.component.html',
  styleUrls: ['./haendler-verkauf.component.css']
})
export class HaendlerVerkaufComponent implements OnInit {
  name: string;
  inventar: InventarPosten[] = [
    { produkt: "Pimmelkäse", vorrat: 3, preis: 3.4, verwendet: false },
    { produkt: "Eichelkäse", vorrat: 2, preis: 1.7, verwendet: false },
    { produkt: "Emmentaler", vorrat: 10, preis: 2.2, verwendet: true }
  ];
  verkauf: VerkaufsPosten[] = [{ produkt: "Emmentaler", menge: 3, preis: 6.6 }];
  columns: string[] = ["Produkt", "Menge", "Preis"];
  neuesProdukt: string = "";

  constructor(private http: HttpClient, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((p) => {
      this.name = p.get("name");
    });
  }

  neuerPosten() {

  }

}


export interface InventarPosten {
  produkt: string;
  vorrat: number;
  preis: number;
  verwendet: boolean;
}

export interface VerkaufsPosten {
  produkt: string;
  menge: number;
  preis: number;
}