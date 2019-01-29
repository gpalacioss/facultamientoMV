import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Compania} from '../../models/compania';

@Injectable({
  providedIn: 'root'
})
export class CompaniaService {

  private  url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  public getCompaniaByNombre(nombreCompania: string): Observable<Compania> {
    return this.http.get<Compania>(this.url + 'getCompaniaByNombre/' + nombreCompania);
  }
}
