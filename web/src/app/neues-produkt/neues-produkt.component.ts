import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-neues-produkt',
  templateUrl: './neues-produkt.component.html',
  styleUrls: ['./neues-produkt.component.css']
})
export class NeuesProduktComponent implements OnInit {
  name: string;
  preis: number;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.name = null;
    this.preis = null;
  }

  produktAnlegen() {
    this.http.post("/api/produkt", { name: this.name, preis: this.preis }).subscribe(e => {
      console.log("pimmel");
    });
    this.router.navigate(['/app-verwaltung-menu']);
  }
}
