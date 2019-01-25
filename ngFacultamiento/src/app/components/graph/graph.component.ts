import {UsuarioService} from './../../service/usuario/usuario.service';
import {Alchemy} from '../../../assets/alchemy/alchemy.js';
import {Component, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FacultadService} from '../../service/facultad/facultad.service';
import {ElementDef} from '@angular/core/src/view';
import {element} from 'protractor';
import {clearResolutionOfComponentResourcesQueue} from '@angular/core/src/metadata/resource_loading';





@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

  private jsonGraph: Object;
  @ViewChild('alchemy') element: ElementDef;

  constructor(
    private usuarioService: UsuarioService,
    private activateRoute: ActivatedRoute,
    private facultadService: FacultadService,
    private renderer: Renderer2
  ) { }

  ngOnInit() {

    let opcion: String;

    this.activateRoute.params.subscribe(params => {
      opcion = params['tipo'];
    });

    console.log('valor de opcion' + opcion);

    switch (opcion) {
      case 'usuarios': {
        this.getUsuariosByUsuarioAdministrador('Jorge Brant');
        break;
      }
      case 'permisoCuentaMonto': {
        this.getPermisoCuentaMontoByUsuario('Francisco Armani');
        break;
      }
      case 'permisoSimple': {
        this.getPermisosusuarioGraphByNombre('Ricardo Legorreta');
        break;
      }
      case 'cuentas': {
        console.log('Poor');
        break;
      }
      default: {
        console.log('dato incorrectoe');
        break;
      }
    }
  }


  public getUsuariosByUsuarioAdministrador(nombreAdmin: String){
    this.usuarioService.getUsuariosByUsuarioAdministrador(nombreAdmin).subscribe(result => {
      this.jsonGraph = result;
      this.generaGraph(this.jsonGraph);
      this.getInfoClick();
    });
  }

  public getPermisosusuarioGraphByNombre(nombreUsuario: String): void {

    this.facultadService.permisoSimpleByUsuarioGraph(nombreUsuario).subscribe(result => {
        this.jsonGraph = result;
        this.generaGraph(this.jsonGraph);
    });
  }

  public getPermisoCuentaMontoByUsuario(nombreUsuario: String): void {
    this.facultadService.getPermisoCuentaMontoByUsuarioGraph(nombreUsuario).subscribe(result => {
        this.jsonGraph = result;
        this.generaGraph(this.jsonGraph);

    });
  }

  public getInfoClick(): void{
      console.log(this.element);
  }

  public  generaGraph(json: Object) {
    const dataSource = json;

        console.log(dataSource);

       const config = {
          dataSource,
         edgeTypes: {'edgeType':['MEMBER_OF', 'TRABAJA_EN', 'CHILD_OF', 'ALLOW']},
         nodeTypes: {'nodeType':['usuario', 'compania', 'grupo', 'permiso', 'cuenta', 'rol', 'perfil']},
         directedEdges: true,
         forceLocked: false,
         nodeCaption: 'name',
         edgeCaption: 'edgeType',
         nodeCaptionsOnByDefault: true,
         nodeStyle: {
           'usuario': {
             'color'      : '#F6F',
             'radius'     : 20,
             'borderWidth': 8
           },
           'compania': {
             'color'      : '#f2eb29',
             'radius'     : 20,
             'borderWidth': 8
           },
           'grupo': {
             'color'      : '#f7a204',
             'radius'     : 20,
             'borderWidth': 8
           },
           'permiso': {
             'color'      : '#f7a204',
             'radius'     : 20,
             'borderWidth': 8
           },
           'cuenta': {
             'color'      : '#7a49ed',
             'radius'     : 20,
             'borderWidth': 8
           },
           'rol': {
             'color'      : '#28f416',
             'radius'     : 20,
             'borderWidth': 8
           },
           'perfil': {
             'color'      : '#f7043d',
             'radius'     : 20,
             'borderWidth': 8
           }},
         edgeStyle: {
           'MEMBER_OF': {
             'width': 5,
             'color': '#F6F'
           },
           'TRABAJA_EN': {
             'width': 8,
             'color': '#f2eb29'
           },
           'CHILD_OF': {
             'width': 8,
             'color': '#f2eb29'
           },
           'ALLOW': {
             'width': 8,
             'color': '#f7a204'
           }
         },
            };

          const alchemy = new Alchemy(config);
  }

}
