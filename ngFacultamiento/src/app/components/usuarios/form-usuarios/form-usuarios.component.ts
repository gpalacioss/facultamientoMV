import { UsuarioService } from './../../../service/usuario/usuario.service';
import { Usuario } from './../../../models/usuario';
import { Component, OnInit } from '@angular/core';

/**
 * El router sirve para navegar entre paginas
 * y el activate Router para traer informacion de una ruta especifica
 */
import { Router, ActivatedRoute} from '@angular/router';

// Libreria de sweetalert2 para manejar alertas personalizadas
import swal from 'sweetalert2';

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './form-usuarios.component.html',
  styleUrls: ['./form-usuarios.component.scss']
})
export class FormUsuariosComponent implements OnInit {

  private usuario: Usuario = new Usuario();

  private titulo: String = 'Crear Usuario';

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
      const idUsuario: number = params['idUsuario'];
      if (idUsuario) {
        this.usuarioService.getUsuarioById(idUsuario).subscribe(result => {
          this.usuario = result;
        });
      }
    });
  }

  public guardaUsuario(accion: number): void {

    let mensaje: String;
    let titulo: string;

    if (accion === 1) {
      mensaje = 'el Usuario ' + this.usuario.nombre + ' fue creador con exito';
      titulo = 'Usuario Guardado';
    } else {
      mensaje = 'el Usuario ' + this.usuario.nombre + ' fue Actualizado con exito';
      titulo = 'Usuario Actualizado';
    }

    this.usuarioService.guardaUsuario(this.usuario).subscribe(result => {
      this.router.navigate(['/user']);
      swal(titulo, mensaje, 'success');
    });

  }

}
