import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Perfil } from 'src/app/models/perfil';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  constructor(private http: HttpClient) { }


  public getPerfiles(): Observable<Perfil[]> {
    return this.http.get<Perfil[]>("http://localhost:8080/getPerfilesNuevos");
  }

}
