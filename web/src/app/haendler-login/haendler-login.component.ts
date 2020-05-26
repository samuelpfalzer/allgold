import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-haendler-login',
  templateUrl: './haendler-login.component.html',
  styleUrls: ['./haendler-login.component.css']
})
export class HaendlerLoginComponent implements OnInit {
  verkaufsstellen: any;
  name: string;

  constructor(private http: HttpClient) {
    http.get("/api/verkaufsstelle").subscribe((elem) => {
      this.verkaufsstellen = elem;
    });
  }

  ngOnInit(): void {
  }

}
