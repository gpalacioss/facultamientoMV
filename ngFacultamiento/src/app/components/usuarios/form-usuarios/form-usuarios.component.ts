import { HttpClient } from '@angular/common/http';
import { UsuarioService } from './../../../service/usuario/usuario.service';
import { Usuario } from './../../../models/usuario';
import { Component, OnInit } from '@angular/core';

/**
 * El router sirve para navegar entre paginas 
 * y el activate Router para traer informacion de una ruta especifica
 */
import { Router, ActivatedRoute} from '@angular/router';

//Libreria de sweetalert2 para manejar alertas personalizadas 
import swal from 'sweetalert2'

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './form-usuarios.component.html',
  styleUrls: ['./form-usuarios.component.scss']
})
export class FormUsuariosComponent implements OnInit {

  private usuario: Usuario = new Usuario();

  private titulo: String = "Crear Usuario";

  private alerta: boolean = true;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private activateRoute: ActivatedRoute
    ) { }

  ngOnInit() {

    this.actualizaUsuario()

  }

  public actualizaUsuario(): void{
    this.activateRoute.params.subscribe(params => {
      let idUsuario: number = params['idUsuario'];
      if(idUsuario){
        this.usuarioService.getUsuarioById(idUsuario).subscribe(result => {
          this.usuario = result;
        })
      }
    })
  }

  public validaCampos(): boolean{
    this.usuario.email == ""

    return null;
  }

  public guardaUsuario(): void{

    this.usuarioService.guardaUsuario(this.usuario).subscribe(result =>{
      this.router.navigate(['/user']);
      swal("Usuario Guardado", 'Usuario creado con exito', "success")
    });
  }

}
