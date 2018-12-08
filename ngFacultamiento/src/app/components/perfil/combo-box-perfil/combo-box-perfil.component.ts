import { PerfilService } from './../../../service/perfil/perfil.service';
import { Component, OnInit } from '@angular/core';
import { Perfil } from 'src/app/models/perfil';

@Component({
  selector: 'app-combo-box-perfil',
  templateUrl: './combo-box-perfil.component.html',
  styleUrls: ['./combo-box-perfil.component.scss']
})
export class ComboBoxPerfilComponent implements OnInit {

  constructor(private perfilService: PerfilService) { }

  private lstPerfiles: Array<Perfil>;

  ngOnInit() {

    this.getPerfiles();
  }

  public getPerfiles(): void{
    this.perfilService.getPerfiles().subscribe(result => {
      this.lstPerfiles = result;
    });
  }

}
