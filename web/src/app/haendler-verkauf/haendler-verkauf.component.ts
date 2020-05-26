import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-haendler-verkauf',
  templateUrl: './haendler-verkauf.component.html',
  styleUrls: ['./haendler-verkauf.component.css']
})
export class HaendlerVerkaufComponent implements OnInit {
  name: string = "Pimmel";

  constructor() { }

  ngOnInit(): void {
  }

}
