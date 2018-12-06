import { PerfilService } from './../../service/perfil/perfil.service';
import { Component, OnInit } from '@angular/core';
import { Perfil } from 'src/app/models/perfil';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {

  private lstPerfiles: Array<Perfil>

  constructor(private perfilService: PerfilService) { }

  ngOnInit() {

    this.getAllPerfiles();
    
  }


  private getAllPerfiles(): void {
    this.perfilService.getPerfiles().subscribe(result => {
      this.lstPerfiles = result;
    });
  }
}
