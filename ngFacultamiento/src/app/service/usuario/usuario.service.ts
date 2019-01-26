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

  public guardaUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>('http://localhost:8080/saveOrUpdateUsuario', usuario);
  }

  public  getUsuarioById(idUsuario: number): Observable<Usuario>{
    return this.http.get<Usuario>(`http://localhost:8080/getUsuarios/ ${idUsuario}`);
  }


  public getUsuariosByUsuarioAdministrador(nombreAdmin: String): Observable<Object>{
    return this.http.get('http://localhost:8080/getEmpresaAndEmpleadosByAdministrador/' + nombreAdmin);
  }

  public getUsuarioByUsername(username: String): Observable<Usuario> {
    return this.http.get<Usuario>('http://localhost:8080/getUsuarioByUsername/' + username);
  }

  public deleteUsuario(id: number): Observable<Usuario> {
    return this.http.delete<Usuario>('http://localhost:8080/deleteUser/' + id);
  }

}
