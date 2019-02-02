import { Component, OnInit } from '@angular/core';
import {Rol} from '../../../models/rol';
import swal from 'sweetalert2';
import {RolService} from '../../../service/rol/rol.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-rol',
  templateUrl: './form-rol.component.html',
  styleUrls: ['./form-rol.component.scss']
})
export class FormRolComponent implements OnInit {

  public rol: Rol = new Rol();

  constructor(
    private rolService: RolService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }


  public saveRol(accion: number, rol: Rol): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/rol';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'El rol' + rol.nombreRol + ' se guardo con exito';
        tituloMensaje = 'rol Guardado';
      } else {
        mensaje = 'El rol ' + rol.nombreRol + ' se Actualizo con exito';
        tituloMensaje = 'rol Actualizado';
      }

      this.rolService.saveRol(rol).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }

}
