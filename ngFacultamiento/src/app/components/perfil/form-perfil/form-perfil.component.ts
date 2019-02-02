import { Component, OnInit } from '@angular/core';
import {Perfil} from '../../../models/perfil';
import swal from 'sweetalert2';
import {PerfilService} from '../../../service/perfil/perfil.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-perfil',
  templateUrl: './form-perfil.component.html',
  styleUrls: ['./form-perfil.component.scss']
})
export class FormPerfilComponent implements OnInit {

  public perfil: Perfil = new Perfil();

  constructor(
    private perfilService: PerfilService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }

  public savePerfil(accion: number, perfil: Perfil): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/perfil';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'El perfil ' + perfil.nombre + ' se guardo con exito';
        tituloMensaje = 'perfil Guardada';
      } else {
        mensaje = 'El perfil ' + perfil.nombre + ' se Actualizo con exito';
        tituloMensaje = 'perfil Actualizada';
      }

      this.perfilService.savePerfil(perfil).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }

}
