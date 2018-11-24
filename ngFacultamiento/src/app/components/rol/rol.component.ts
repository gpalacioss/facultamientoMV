import { RolService } from './../../service/rol/rol.service';
import { Component, OnInit } from '@angular/core';
import { Rol } from 'src/app/models/rol';

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrls: ['./rol.component.scss']
})
export class RolComponent implements OnInit {

  private lstRol: Array<Rol>;

  constructor(private rolService: RolService) { }

  ngOnInit() {
    this.getAllRoles();
  }


  private getAllRoles(): void {

    this.rolService.getAllRoles().subscribe(result => {
      this.lstRol = result;
    });

  }
}
