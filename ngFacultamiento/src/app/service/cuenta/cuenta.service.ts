import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cuenta} from '../../models/cuenta';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {

  private  url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  public getCuentaByNumCuenta(numCuenta: string): Observable<Cuenta> {
    return this.http.get<Cuenta>(this.url + 'getCuentaByNumerocuenta/' + numCuenta);
  }


}
