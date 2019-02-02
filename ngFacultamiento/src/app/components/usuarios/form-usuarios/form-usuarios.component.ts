import { UsuarioService } from './../../../service/usuario/usuario.service';
import { Usuario } from './../../../models/usuario';
import {Component, OnInit} from '@angular/core';

/**
 * El router sirve para navegar entre paginas
 * y el activate Router para traer informacion de una ruta especifica
 */
import {Router, ActivatedRoute, Params} from '@angular/router';

// Libreria de sweetalert2 para manejar alertas personalizadas
import swal from 'sweetalert2';
import {after} from 'selenium-webdriver/testing';

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './form-usuarios.component.html',
  styleUrls: ['./form-usuarios.component.scss']
})
export class FormUsuariosComponent implements OnInit {

  public usuario: Usuario = new Usuario();

  private tituloForm: String = 'Crear Usuario';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private activateRoute: ActivatedRoute
    ) { }

  ngOnInit() {
    this.actualizaUsuario();
  }

  public actualizaUsuario(): void {
    this.activateRoute.params.subscribe(params => {
      console.log(params);
      const idUsuario: number = params['idUsuario'];
      if (idUsuario) {
        this.usuarioService.getUsuarioById(idUsuario).subscribe(result => {
          this.usuario = result;
        });
      }
    });
  }

  public guardaUsuario(accion: number, usuario: Usuario): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activateRoute.params.subscribe(p => {
      tipo = p['tipo'];

    if (tipo === undefined) {
      urlReturn = '/user';
    } else {
      urlReturn = '/graph/' + tipo;
    }

    if (accion === 1) {
      mensaje = 'el Usuario ' + usuario.nombre + ' fue creador con exito';
      tituloMensaje = 'Usuario Guardado';
    } else {
      mensaje = 'el Usuario ' + usuario.nombre + ' fue Actualizado con exito';
      tituloMensaje = 'Usuario Actualizado';
    }

    this.usuarioService.guardaUsuario(usuario).subscribe(result => {
      console.log(urlReturn);
        this.router.navigate([urlReturn]);
      swal(tituloMensaje, mensaje, 'success');
    });
    });
  }

}
