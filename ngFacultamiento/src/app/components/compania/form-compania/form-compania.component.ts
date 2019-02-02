import { Component, OnInit } from '@angular/core';
import {Compania} from '../../../models/compania';
import {CompaniaService} from '../../../service/compania/compania.service';
import {ActivatedRoute, Router} from '@angular/router';

import swal from 'sweetalert2';

@Component({
  selector: 'app-form-compania',
  templateUrl: './form-compania.component.html',
  styleUrls: ['./form-compania.component.scss']
})
export class FormCompaniaComponent implements OnInit {

  public compania: Compania = new Compania();
  private titulo: String = 'Compañia';

  constructor(
    private companiaService: CompaniaService,
    private router: Router,
    private activateRoute: ActivatedRoute
  ) { }

  ngOnInit() {

  }

  public actualizaCompania(): void {
      this.activateRoute.params.subscribe(params => {
        const nombreCompania: string = params['nombreCompania'];
        console.log(nombreCompania);
        this.companiaService.getCompaniaByNombre(nombreCompania).subscribe(result => {
          this.compania = result;
        });
      });
  }


  public saveCompania(accion: number, compania: Compania): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activateRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/compania';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'La Compania ' + compania.nombreCompania + ' se guardo con exito';
        tituloMensaje = 'Compañia Guardada';
      } else {
        mensaje = 'La Compania ' + compania.nombreCompania + ' se Actualizo con exito';
        tituloMensaje = 'Compañia Actualizada';
      }

      this.companiaService.saveCompania(compania).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }

}
