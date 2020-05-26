import { Component, OnInit } from '@angular/core';
import { LieferantenService } from '../lieferanten.service';
import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface ProduktMengeRow {
  produkt: string;
  menge: number;
}

export interface Lieferung {
  name: string;
  ort: string;
  data: ProduktMengeDataSource;
}


@Component({
  selector: 'app-lieferplan',
  templateUrl: './lieferplan.component.html',
  styleUrls: ['./lieferplan.component.css']
})
export class LieferplanComponent implements OnInit {
  columns: string[] = ["Produkt", "Menge"];
  lieferungen: Lieferung[] = [];

  constructor(private http: HttpClient) {
    this.refreshLieferplan();
  }

  ngOnInit(): void { }

  refreshLieferplan() {
    this.lieferungen = [];
    this.http.get("/api/lieferung").subscribe((data: any[]) => {
      data.forEach(element => {
        let lieferung: Lieferung = { name: element.name, ort: element.ort, data: new ProduktMengeDataSource(element.posten) };
        this.lieferungen.push(lieferung);
      })
    });
  }

  geliefert(name: string) {
    let lieferung: Lieferung = this.lieferungen.find((x) => x.name === name);
    this.http.post("/api/lieferung",
      {
        verkaufsstelle: lieferung.name,
        posten: lieferung.data.data.value
      }
    ).subscribe((x) => {
      console.log(x);
      this.refreshLieferplan();
    });
  }
}


export class ProduktMengeDataSource extends DataSource<ProduktMengeRow> {
  data: BehaviorSubject<ProduktMengeRow[]>;

  constructor(x: ProduktMengeRow[]) {
    super();
    this.data = new BehaviorSubject<ProduktMengeRow[]>(x);
  }

  connect(): Observable<ProduktMengeRow[]> {
    return this.data;
  }

  disconnect() {

  }
}