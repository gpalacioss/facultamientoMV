import {UsuarioService} from './../../service/usuario/usuario.service';
import {Alchemy} from '../../../assets/alchemy/alchemy.js';
import {ActivatedRoute, Router} from '@angular/router';
import {FacultadService} from '../../service/facultad/facultad.service';
import {Usuario} from '../../models/usuario';
import {CuentaService} from '../../service/cuenta/cuenta.service';
import {GrupoService} from '../../service/grupo/grupo.service';
import {CompaniaService} from '../../service/compania/compania.service';
import {PerfilService} from '../../service/perfil/perfil.service';
import {RolService} from '../../service/rol/rol.service';
import {FormUsuariosComponent} from '../usuarios/form-usuarios/form-usuarios.component';
import {Component, OnInit, ViewChild} from '@angular/core';
import {FormCompaniaComponent} from '../compania/form-compania/form-compania.component';
import {FormGrupoComponent} from '../grupo/form-grupo/form-grupo.component';
import {FormCuentaComponent} from '../cuenta/form-cuenta/form-cuenta.component';
import {FormPerfilComponent} from '../perfil/form-perfil/form-perfil.component';
import {FormRolComponent} from '../rol/form-rol/form-rol.component';
import {FormPermisoComponent} from '../permiso/form-permiso/form-permiso.component';
import {FormPermisoCuentaComponent} from '../permiso-cuenta-monto/form-permiso-cuenta/form-permiso-cuenta.component';
import {Permiso} from '../../models/permiso';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {

  private jsonGraph: Object;
  private tipo = 'opcion';
  private usuario: Usuario = new Usuario();
  private permiso: Permiso = new Permiso();

  @ViewChild(FormPermisoCuentaComponent) frmCuentaMonto: FormPermisoCuentaComponent;
  @ViewChild(FormUsuariosComponent) formUsuariosComponent: FormUsuariosComponent;
  @ViewChild(FormCompaniaComponent) formCompaniaComponent: FormCompaniaComponent;
  @ViewChild(FormGrupoComponent) formGrupoComponent: FormGrupoComponent;
  @ViewChild(FormCuentaComponent)  formCuentaComponent: FormCuentaComponent;
  @ViewChild(FormPerfilComponent) formPerfilComponent: FormPerfilComponent;
  @ViewChild(FormRolComponent) formRolComponen: FormRolComponent;
  @ViewChild(FormPermisoComponent) formPermisoComponent: FormPermisoComponent;

  constructor(
    private usuarioService: UsuarioService,
    private activateRoute: ActivatedRoute,
    private facultadService: FacultadService,
    private cuentaService: CuentaService,
    private grupoService: GrupoService,
    private companiaService: CompaniaService,
    private perfilService: PerfilService,
    private rolService: RolService,
    private router: Router

  ) { }

  ngOnInit() {
    let opcion: String;

    this.activateRoute.params.subscribe(params => {
      opcion = params['tipo'];


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
        this.getPermisosusuarioGraphByNombre('Zaira Casimiro');
        break;
      }

      case 'permisoCuentaMontoop': {
        this.getPermisoCuentaMontoByUsuario('Rogelio Zarate Mendez');
        break;
      }
      case 'permisoSimpleop': {
        this.getPermisosusuarioGraphByNombre('Herwin Toral');
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
    });
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

    $(node).dblclick(function() {
      const nombre = $(this).text();
      const id = $(this).attr('id').split('-')[1];
      nvo_this.tipo = $(this).attr('class').split(' ')[1];
      nvo_this.getInformacionNodoSeleccionado(nvo_this.tipo, nombre, parseInt(id));
    });
  }



  getInformacionNodoSeleccionado(tipo: string, nombre: string, id: number): void  {

    switch (tipo) {
      case 'usuario': {
        this.usuarioService.getUsuarioByNombre(nombre).subscribe(result => {
          this.formUsuariosComponent.usuario = result;
        });
        break;
      }
      case 'administrador': {
        this.usuarioService.getUsuarioByNombre(nombre).subscribe(result => {
          this.formUsuariosComponent.usuario = result;
        });
        break;
      }
      case 'permiso': {
        this.facultadService.getPermisoByNombre(nombre).subscribe(result => {
          this.formPermisoComponent.permiso = result;
          this.permiso = result;
        });
        break;
      }

      case 'perfil': {
        this.perfilService.getPerfilByNombre(nombre).subscribe(result => {
          this.formPerfilComponent.perfil = result;
        });
        break;
      }

      case 'rol': {
        this.rolService.getRolByNombre(nombre).subscribe(result => {
          this.formRolComponen.rol = result;
        });
        break;
      }

      case 'permisoCuentaMonto': {
        this.facultadService.getPermisoCuentaMontoById(id).subscribe(result => {
          console.log(result);
          this.frmCuentaMonto.permisCuentaMonto = result;
        });
        break;
      }

      case 'cuenta': {
        this.cuentaService.getCuentaByNumCuenta(nombre).subscribe(result => {
          this.formCuentaComponent.cuenta = result;
        });
        break;
      }
      case 'grupo': {
        this.grupoService.getGrupoByNombre(nombre).subscribe(result => {
          this.formGrupoComponent.grupo = result;
        });
        break;
      }

      case 'compania': {
        this.companiaService.getCompaniaByNombre(nombre).subscribe(result => {
          this.formCompaniaComponent.compania = result;
        });
        break;
      }
    }
  }

  public eliminaPermiso(permiso: Permiso): void {
      this.facultadService.eliminaPermiso(permiso).subscribe(result => {
        const url = '/graph/permisoSimple';
       this.router.navigate([url]);
       window.location.reload();
    });
  }

  public  generaGraph(json: Object) {
    const dataSource = json;

        console.log(dataSource);

       const config = {
          dataSource,
         edgeTypes: {'edgeType': ['MEMBER_OF', 'WORKS_FOR', 'TRABAJA_EN', 'CHILD_OF', 'ALLOW']},
         // tslint:disable-next-line:max-line-length
         nodeTypes: {'nodeType': ['usuario', 'compania', 'grupo', 'permiso', 'cuenta', 'rol', 'perfil', 'permisoCuentaMonto', 'administrador']},
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
           'administrador': {
             'color'      : '#f40c0f',
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
           'WORKS_FOR': {
             'width': 8,
             'color': '#f2eb29'
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
