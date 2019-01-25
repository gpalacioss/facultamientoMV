import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario/usuario.service';
import { Usuario } from 'src/app/models/usuario';
import sweetAlert from 'sweetalert2';


@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {

  public userLis: Array<Usuario>;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.getUsuarios();
  }

  getUsuarios(): void {
    this.usuarioService.getAllUsuarios().subscribe(result => {
      this.userLis = result;
    });
  }

  deleteusuario(usuario: Usuario): void {
    sweetAlert({
      title: 'Eliminar?',
      text: 'Esta seguro que desea eliminar!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Eliminar!',
      cancelButtonText: 'No, Cancelarl!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.usuarioService.deleteUsuario(usuario.id).subscribe(r => {
          this.userLis = this.userLis.filter(u => u !== usuario);
          sweetAlert('Usuario Eliminado', 'El usuario : ' + usuario.nombre + ' Fue eliminado Correctamente', 'success');
        });
      }

      });
  }

}
