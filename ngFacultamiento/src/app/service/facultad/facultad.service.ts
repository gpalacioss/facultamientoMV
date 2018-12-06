import { Observable } from 'rxjs';
import { Permiso } from '../../models/Permiso';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FacultadService {

  constructor(private http: HttpClient) { }

  public  getPermisos(): Observable<Permiso[]> {
    return this.http.get<Permiso[]>('http://localhost:8080/getPermisos')
  }


}
