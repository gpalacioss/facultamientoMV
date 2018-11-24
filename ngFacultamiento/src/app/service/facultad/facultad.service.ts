import { Observable } from 'rxjs';
import { Facultad } from './../../models/facultad';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FacultadService {

  constructor(private http: HttpClient) { }

  public  getFacultades(): Observable<Facultad[]> {
    return this.http.get<Facultad[]>('http://localhost:8080/getFacultades')
  }


}
