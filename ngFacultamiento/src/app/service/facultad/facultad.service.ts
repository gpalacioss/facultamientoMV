import { Observable } from 'rxjs';
import { Permiso } from '../../models/permiso';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {PermisoCuentaMonto} from '../../models/permiso-cuenta-monto';

@Injectable({
  providedIn: 'root'
})

export class FacultadService {

  constructor(private http: HttpClient) { }

  private  url: string = 'http://localhost:8080/';

  public  getPermisos(): Observable<Permiso[]> {
    return this.http.get<Permiso[]>('http://localhost:8080/getPermisos');
  }

  public getPermisoCuentaMonto(): Observable<PermisoCuentaMonto[]> {
    return this.http.get<PermisoCuentaMonto[]>('http://localhost:8080/getPermisosCuentaMonto');
  }

  public permisoSimpleByUsuarioGraph(nombreUsuario: String): Observable<Object> {
    return this.http.get(`http://localhost:8080/usuariosAndPermisosGraph/${nombreUsuario}`);
  }

  public getPermisoCuentaMontoByUsuarioGraph(nombreUsuario: String): Observable<Object> {
    return this.http.get('http://localhost:8080/getPermisosCuentaMontoByUsuario/' + nombreUsuario);
  }


  public getPermisoByNombre(nombrePermiso: String): Observable<Permiso> {
    return this.http.get<Permiso>(this.url + 'getPermisoByNombre/' + nombrePermiso);
  }

  public getPermisoCuentaMontoById(id: number): Observable<PermisoCuentaMonto>{
    return this.http.get<PermisoCuentaMonto>(this.url + 'getPermisoCuentaById/' + id);
  }
}
