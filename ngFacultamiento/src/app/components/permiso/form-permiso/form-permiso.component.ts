import { Component, OnInit } from '@angular/core';
import {Permiso} from '../../../models/permiso';
import {Grupo} from '../../../models/grupo';
import swal from 'sweetalert2';
import {FacultadService} from '../../../service/facultad/facultad.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-permiso',
  templateUrl: './form-permiso.component.html',
  styleUrls: ['./form-permiso.component.scss']
})
export class FormPermisoComponent implements OnInit {

  public permiso: Permiso = new Permiso();

  constructor(
    private permisoService: FacultadService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }


  public savePermiso(accion: number, permiso: Permiso): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/permiso';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'El permiso' + permiso.nombre + ' se guardo con exito';
        tituloMensaje = 'permiso Guardado';
      } else {
        mensaje = 'El permiso ' + permiso.nombre + ' se Actualizo con exito';
        tituloMensaje = 'permiso Actualizado';
      }

      this.permisoService.savePermiso(permiso).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }

}
