import { Component, OnInit } from '@angular/core';
import {Cuenta} from '../../../models/cuenta';
import swal from 'sweetalert2';
import {CuentaService} from '../../../service/cuenta/cuenta.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-form-cuenta',
  templateUrl: './form-cuenta.component.html',
  styleUrls: ['./form-cuenta.component.scss']
})
export class FormCuentaComponent implements OnInit {

  public cuenta: Cuenta = new Cuenta();

  constructor(
    private cuentaService: CuentaService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }

  public saveCuenta(accion: number, cuenta: Cuenta): void {

    let mensaje: string;
    let tituloMensaje: string;
    let tipo: string | undefined;
    let urlReturn: string;

    this.activatedRoute.params.subscribe(p => {
      tipo = p['tipo'];

      if (tipo === undefined) {
        urlReturn = '/cuenta';
      } else {
        urlReturn = '/graph/' + tipo;
      }

      if (accion === 1) {
        mensaje = 'La Cuenta ' + cuenta.numeroCuenta + ' se guardo con exito';
        tituloMensaje = 'Cuenta Guardada';
      } else {
        mensaje = 'La Cuenta ' + cuenta.numeroCuenta + ' se Actualizo con exito';
        tituloMensaje = 'Cuenta Actualizada';
      }

      this.cuentaService.saveCuenta(cuenta).subscribe(result => {
        console.log(urlReturn);
        this.router.navigate([urlReturn]);
        swal(tituloMensaje, mensaje, 'success');
      });
    });
  }

}
