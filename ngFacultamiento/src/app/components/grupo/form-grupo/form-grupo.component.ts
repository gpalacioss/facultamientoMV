import { Component, OnInit } from '@angular/core';
import {Grupo} from '../../../models/grupo';
import swal from 'sweetalert2';
import {GrupoService} from '../../../service/grupo/grupo.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-grupo',
  templateUrl: './form-grupo.component.html',
  styleUrls: ['./form-grupo.component.scss']
})
export class FormGrupoComponent implements OnInit {

  public grupo: Grupo = new Grupo();
  constructor(
    private grupoService: GrupoService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }


  public saveGrupo(accion: number, grupo: Grupo): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/grupo';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'El grupo' + grupo.nombre + ' se guardo con exito';
        tituloMensaje = 'Grupo Guardado';
      } else {
        mensaje = 'El Grupo ' + grupo.nombre + ' se Actualizo con exito';
        tituloMensaje = 'Grupo Actualizado';
      }

      this.grupoService.saveGrupo(grupo).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }
}
