import { Rol } from './../../models/rol';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  constructor(private http: HttpClient) { }


  public getAllRoles(): Observable<Rol[]> {

    return this.http.get<Rol[]>("http://localhost:8080/getAllRolles");
  }

}
