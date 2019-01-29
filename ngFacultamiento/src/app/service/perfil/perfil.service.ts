import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Perfil } from 'src/app/models/perfil';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private  url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  public getPerfiles(): Observable<Perfil[]> {
    return this.http.get<Perfil[]>(this.url  + 'getPerfilesNuevos');
  }

  public getPerfilByNombre(nombrePerfil: string): Observable<Perfil> {
    return this.http.get<Perfil>(this.url + 'getPerfilByNombre/' + nombrePerfil);
  }

}
