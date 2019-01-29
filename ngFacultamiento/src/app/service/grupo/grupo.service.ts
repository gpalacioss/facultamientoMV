import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Grupo} from '../../models/grupo';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  private  url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  public getGrupoByNombre(nombreGrupo): Observable<Grupo> {
    return this.http.get<Grupo>(this.url + 'getGrupoByNombre/' + nombreGrupo);
  }
}
