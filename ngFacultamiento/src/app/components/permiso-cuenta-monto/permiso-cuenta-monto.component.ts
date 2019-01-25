import { Component, OnInit } from '@angular/core';
import {FacultadService} from '../../service/facultad/facultad.service';
import {PermisoCuentaMonto} from '../../models/permiso-cuenta-monto';

@Component({
  selector: 'app-permiso-cuenta-monto',
  templateUrl: './permiso-cuenta-monto.component.html',
  styleUrls: ['./permiso-cuenta-monto.component.scss']
})
export class PermisoCuentaMontoComponent implements OnInit {

  public lstPermisoCuentaMonto: Array<PermisoCuentaMonto>;

  constructor(private facultadService: FacultadService) { }

  ngOnInit() {
    this.getPermisoCuentaMonto();
  }


  public getPermisoCuentaMonto(): void {
    this.facultadService.getPermisoCuentaMonto().subscribe(r => {
      this.lstPermisoCuentaMonto = r;
    });
  }
}
