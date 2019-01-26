import {UsuarioService} from './../../service/usuario/usuario.service';
import {Alchemy} from '../../../assets/alchemy/alchemy.js';
import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FacultadService} from '../../service/facultad/facultad.service';
import {Usuario} from '../../models/usuario';
import {element} from 'protractor';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

  private jsonGraph: Object;
  @ViewChild('alchemy') element: ElementRef;

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

    console.log('valor de opcion ' + opcion);

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

  public getInfoClick(): void {

    this.renderer.listen(this.element.nativeElement, 'click', (event) => {
      console.log('hola');
    });

    const node = $('g[id^="node-"]');
    node.click(function () {
      const nombre = $(this).text();

      const id = $(this).attr('id').split('-')[1];
      const tipo = $(this).attr('class').split(' ')[1];


      console.log(nombre);
      console.log(tipo);
      console.log(id);
      this.getInformacionNodoSeleccionado(tipo, nombre, id);
    });
  }



     getInformacionNodoSeleccionado(tipo: String, nombre: String, id: number): void  {

    switch (tipo) {
      case 'usuario': {

        let usuario: Usuario;
        this.usuarioService.getUsuarioByUsername(nombre).subscribe(result => {
          usuario = result;
          console.log(usuario.nombre);
        });
        break;
      }

      case 'permiso': {
        break;
      }

      case 'puentaMOnto': {
        break;
      }

      case 'cuenta': {
        break;
      }
      case 'grupo': {
        break;
      }

      case 'compania': {
        break;
      }

    }
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
