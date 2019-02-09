import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Perfil } from 'src/app/models/perfil';
import {jsonpCallbackContext} from '@angular/common/http/src/module';
import {Permiso} from '../../models/permiso';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private  url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  public savePerfil(perfil: Perfil): Observable<Perfil> {
    return this.http.post<Perfil>(this.url + 'savePerfil', perfil);
  }

  public getPerfiles(): Observable<Perfil[]> {
    return this.http.get<Perfil[]>(this.url  + 'getPerfilesNuevos');
  }

  public getPerfilByNombre(nombrePerfil: string): Observable<Perfil> {
    return this.http.get<Perfil>(this.url + 'getPerfilByNombre/' + nombrePerfil);
  }

  public agregaPermisosNegados(listaPermisos: Array<Permiso>): Observable<any> {
    console.log(listaPermisos);
    return this.http.post<any>(this.url + 'addPermisos', listaPermisos);
  }

  public addPerfil(perfil: Perfil): Observable<Perfil> {
    return this.http.post<Perfil>(this.url + 'addPerfil', perfil);
  }
}
