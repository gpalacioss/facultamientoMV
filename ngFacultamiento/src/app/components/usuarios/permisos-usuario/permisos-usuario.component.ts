import { Usuario } from './../../../models/usuario';
import { UsuarioService } from './../../../service/usuario/usuario.service';
import { Component, OnInit } from '@angular/core';
import { jsonpCallbackContext } from '@angular/common/http/src/module';

@Component({
  selector: 'app-permisos-usuario',
  templateUrl: './permisos-usuario.component.html',
  styleUrls: ['./permisos-usuario.component.scss']
})
export class PermisosUsuarioComponent implements OnInit {

  public jsonGraphPermisosUsuario: any;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.getPermisosusuarioByNombre("Ricardo Legorreta");
  }

  public getPermisosusuarioByNombre(nombreUsuario: String): void {

    this.usuarioService.permisosUsuarioGraph(nombreUsuario).subscribe(result =>{
        this.jsonGraphPermisosUsuario = result;
        console.log(result);
        console.log(this.jsonGraphPermisosUsuario);
    });

    console.log(this.jsonGraphPermisosUsuario);

  }

}
