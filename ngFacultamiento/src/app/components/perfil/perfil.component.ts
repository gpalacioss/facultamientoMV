import { PerfilService } from './../../service/perfil/perfil.service';
import {Component, OnInit, ViewChild} from '@angular/core';
import { Perfil } from 'src/app/models/perfil';
import {FacultadService} from '../../service/facultad/facultad.service';
import {forEach} from '@angular/router/src/utils/collection';
import {Permiso} from '../../models/permiso';
import {FacultadComponent} from '../permiso/facultad.component';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {

  private lstPerfiles: Array<Perfil>;
  private lstPermisos: Array<Permiso>;
  private tblPerfil = true;
  private perfil: Perfil;
  private lstPermisosNegados: Permiso[];

  @ViewChild(FacultadComponent) facultadComponent: FacultadComponent;

  constructor(
    private perfilService: PerfilService,
    private facultadService: FacultadService
    ) { }

  ngOnInit() {
    this.getAllPerfiles();
  }


  private getAllPerfiles(): void {
    this.perfilService.getPerfiles().subscribe(result => {
      this.lstPerfiles = result;
    });
  }

  public getPermisoByPerfil(perfil: Perfil): void {

    console.log('estamos en el click' + perfil.nombre);
    this.tblPerfil = false;

    this.facultadService.getPermisosByNombrePerfil(perfil.nombre).subscribe(result => {
      this.lstPermisos = result;
      this.lstPermisos.forEach(p => {
        console.log(p.idPermiso + '-' + p.nombre);
      });
    });

    this.perfil = perfil;
  }

  public getValorCheck(permiso: Permiso): void {
    console.log(this.perfil.nombre);
    console.log(permiso.idPermiso);
    console.log(permiso.nombre);
    this.lstPermisosNegados.push(permiso);
  }

  public guardaPermisosNegados(listaPermiso: Array<Permiso>): void {
    this.lstPermisosNegados.forEach(p => {
      console.log(p.nombre);
    });
  }
}
