import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-neue-verkaufsstelle',
  templateUrl: './neue-verkaufsstelle.component.html',
  styleUrls: ['./neue-verkaufsstelle.component.css']
})
export class NeueVerkaufsstelleComponent implements OnInit {
  typ: string;
  name: string;
  ort: string;

  typen: string[] = ["Haendler", "Automat"];

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.typ = null;
    this.name = null;
    this.ort = null;
  }

  eroeffnen() {
    this.http.post("/api/verkaufsstelle", { name: this.name, ort: this.ort, typ: this.typ }).subscribe(e => {
      console.log("pimmel");
    })
  }
}
