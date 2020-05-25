import { Component, OnInit } from '@angular/core';
import { LieferantenService } from '../lieferanten.service';

@Component({
  selector: 'app-lieferplan',
  templateUrl: './lieferplan.component.html',
  styleUrls: ['./lieferplan.component.css']
})
export class LieferplanComponent implements OnInit {
  pimmel: string[];
  columnsToDisplay = ['Produkt'];

  constructor(private service: LieferantenService) {
    this.pimmel = ["Pimmel1", "pimmel2", "pimmel3"];
  }

  ngOnInit(): void {
    this.service.fetchLieferplan();
  }

}
