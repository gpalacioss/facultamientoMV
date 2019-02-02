import { Permiso } from '../../models/permiso';
import { FacultadService } from './../../service/facultad/facultad.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-facultad',
  templateUrl: './facultad.component.html',
  styleUrls: ['./facultad.component.scss']
})
export class FacultadComponent implements OnInit {

  private lstPermisos: Array<Permiso>;

  constructor(private facultadService: FacultadService) { }

  ngOnInit() {
    this.getPermisos();
  }

  public getPermisos(): void{

    this.facultadService.getPermisos().subscribe(result => {
      this.lstPermisos = result;
    })
  }

}
