import { Facultad } from './../../models/facultad';
import { FacultadService } from './../../service/facultad/facultad.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-facultad',
  templateUrl: './facultad.component.html',
  styleUrls: ['./facultad.component.scss']
})
export class FacultadComponent implements OnInit {

  private lstFacultades: Array<Facultad>;

  constructor(private facultadService: FacultadService) { }

  ngOnInit() {
    this.getFacultades();
  }

  public getFacultades(): void {

    this.facultadService.getFacultades().subscribe(result => {

      this.lstFacultades = result;

    });

  }

}
