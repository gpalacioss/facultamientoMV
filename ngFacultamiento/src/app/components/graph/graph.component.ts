import {UsuarioService} from './../../service/usuario/usuario.service';
import {Alchemy} from '../../../assets/alchemy/alchemy.js';
import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FacultadService} from '../../service/facultad/facultad.service';
import {Usuario} from '../../models/usuario';
import {Permiso} from '../../models/permiso';
import {PermisoCuentaMonto} from '../../models/permiso-cuenta-monto';
import {Cuenta} from '../../models/cuenta';
import {CuentaService} from '../../service/cuenta/cuenta.service';
import {GrupoService} from '../../service/grupo/grupo.service';
import {CompaniaService} from '../../service/compania/compania.service';
import {Grupo} from '../../models/grupo';
import {Compania} from '../../models/compania';
import {Perfil} from '../../models/perfil';
import {PerfilService} from '../../service/perfil/perfil.service';
import {RolService} from '../../service/rol/rol.service';
import {Rol} from '../../models/rol';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

  private jsonGraph: Object;
  private tipo: string = 'opcion';
  private usuario: Usuario = new Usuario();

  constructor(
    private usuarioService: UsuarioService,
    private activateRoute: ActivatedRoute,
    private facultadService: FacultadService,
    private cuentaService: CuentaService,
    private grupoService: GrupoService,
    private companiaService: CompaniaService,
    private perfilService: PerfilService,
    private rolService: RolService,
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
        console.log('dato incorrecto');
        break;
      }
    }
  }


  public getUsuariosByUsuarioAdministrador(nombreAdmin: String) {
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
        this.getInfoClick();
    });
  }

  public getPermisoCuentaMontoByUsuario(nombreUsuario: String): void {
    this.facultadService.getPermisoCuentaMontoByUsuarioGraph(nombreUsuario).subscribe(result => {
        this.jsonGraph = result;
        this.generaGraph(this.jsonGraph);
        this.getInfoClick();
    });
  }

  public getInfoClick(): void {
    const nvo_this = this;
    const node = $('g[id^="node-"]');

    $(node).click(function() {

      const nombre = $(this).text();
      const id = $(this).attr('id').split('-')[1];
      nvo_this.tipo = $(this).attr('class').split(' ')[1];

      nvo_this.getInformacionNodoSeleccionado(nvo_this.tipo, nombre, parseInt(id));
    });

  }



  getInformacionNodoSeleccionado(tipo: string, nombre: string, id: number): void  {

    switch (tipo) {
      case 'usuario': {
        this.usuarioService.getUsuarioByUsername(nombre).subscribe(result => {
          this.usuario = result;
          console.log(this.usuario.nombre);
        });

        break;
      }

      case 'permiso': {
        let permiso: Permiso;
        this.facultadService.getPermisoByNombre(nombre).subscribe(result => {
          permiso = result;
          console.log(permiso.nombre);
        });
        break;
      }

      case 'perfil': {
        let perfil: Perfil;
        this.perfilService.getPerfilByNombre(nombre).subscribe(result => {
          perfil = result;
          console.log(perfil.nombre);
        });
        break;
      }

      case 'rol': {
        let rol: Rol;
        this.rolService.getRolByNombre(nombre).subscribe(result => {
          rol = result;
          console.log(rol.nombreRol);
        });
        break;
      }

      case 'permisCuentaMonto': {
        let permisoCuentaMonto: PermisoCuentaMonto;
        this.facultadService.getPermisoCuentaMontoById(id).subscribe(result => {
          permisoCuentaMonto = result;
          console.log(permisoCuentaMonto.permiso.nombre);
        });
        break;
      }
      case 'cuenta': {
        let cuenta: Cuenta;
        this.cuentaService.getCuentaByNumCuenta(nombre).subscribe(result => {
          cuenta = result;
          console.log(cuenta.numeroCuenta);
        });
        break;
      }
      case 'grupo': {
        let grupo: Grupo;
        this.grupoService.getGrupoByNombre(nombre).subscribe(result => {
          grupo = result;
          console.log(grupo.nombre);
        });
        break;
      }

      case 'compania': {
        let compania: Compania;
        this.companiaService.getCompaniaByNombre(nombre).subscribe(result => {
          compania = result;
          console.log(compania.nombreCompania);
        });
        break;
      }
    }
  }

  public  generaGraph(json: Object) {
    const dataSource = json;

        console.log(dataSource);

       const config = {
          dataSource,
         edgeTypes: {'edgeType': ['MEMBER_OF', 'TRABAJA_EN', 'CHILD_OF', 'ALLOW']},
         nodeTypes: {'nodeType': ['usuario', 'compania', 'grupo', 'permiso', 'cuenta', 'rol', 'perfil']},
         directedEdges: true,
         forceLocked: true,
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

  public actualizainfoUsuario(usuario: Usuario) {
    // this.formUsuario.guardaUsuario(2, usuario);
    this.usuarioService.guardaUsuario(usuario);
  }

}
