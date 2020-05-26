import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-haendler-menu',
  templateUrl: './haendler-menu.component.html',
  styleUrls: ['./haendler-menu.component.css']
})
export class HaendlerMenuComponent implements OnInit {
  name: string;
  private sub: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sub = this.route.paramMap.subscribe((p) => {
      this.name = p.get("name");
    });
  }

}
