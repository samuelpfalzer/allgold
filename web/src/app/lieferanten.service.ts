import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LieferantenService {

  public lieferplan: any;

  constructor(private http: HttpClient) { }

  fetchLieferplan() {
    this.http.get("/api/lieferung").subscribe((data) => { this.lieferplan = data; });
  }
}
