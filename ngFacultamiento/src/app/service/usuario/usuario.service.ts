import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { Usuario } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  public getAllUsuarios(): Observable<Usuario[]> {
      return this.http.get<Usuario[]>('http://localhost:8080/getUsuarios');

  }

}
