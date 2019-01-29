import { Rol } from './../../models/rol';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  private  url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }


  public getAllRoles(): Observable<Rol[]> {

    return this.http.get<Rol[]>("http://localhost:8080/getAllRolles");
  }

  public getRolByNombre(nombreRol: string): Observable<Rol> {
    return this.http.get<Rol>(this.url + 'getRolbyNombre/' + nombreRol);
  }

}
