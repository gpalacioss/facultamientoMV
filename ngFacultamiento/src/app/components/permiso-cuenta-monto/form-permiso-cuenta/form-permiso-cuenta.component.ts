import { Component, OnInit } from '@angular/core';
import {PermisoCuentaMonto} from '../../../models/permiso-cuenta-monto';
import swal from 'sweetalert2';
import {FacultadService} from '../../../service/facultad/facultad.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-permiso-cuenta',
  templateUrl: './form-permiso-cuenta.component.html',
  styleUrls: ['./form-permiso-cuenta.component.scss']
})
export class FormPermisoCuentaComponent implements OnInit {

  public permisoCuentaMonto: PermisoCuentaMonto = new PermisoCuentaMonto();

  constructor(
    private permisoService: FacultadService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }


  public savePermisoCuentaMontoCuenta(accion: number, permisoCuentaMonto: PermisoCuentaMonto): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/permisoCuentaMonto';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'El permisoCuentaMonto' + permisoCuentaMonto.permiso.nombre + ' se guardo con exito';
        tituloMensaje = 'permisoCuentaMonto Guardado';
      } else {
        mensaje = 'El permisoCuentaMonto ' + permisoCuentaMonto.permiso.nombre + ' se Actualizo con exito';
        tituloMensaje = 'permisoCuentaMonto Actualizado';
      }

      this.permisoService.savePermisoCuentaMonto(permisoCuentaMonto).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }
}
