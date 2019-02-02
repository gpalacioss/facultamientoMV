import { GraphComponent } from './components/graph/graph.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { RolComponent } from './components/rol/rol.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { FacultadComponent } from './components/permiso/facultad.component';
import { FormUsuariosComponent } from './components/usuarios/form-usuarios/form-usuarios.component';
import {PermisoCuentaMontoComponent} from './components/permiso-cuenta-monto/permiso-cuenta-monto.component';
import {CompaniaComponent} from './components/compania/compania.component';
import {FormCompaniaComponent} from './components/compania/form-compania/form-compania.component';

const routes: Routes = [
  { path: ' ', redirectTo: '/appComponent', pathMatch: 'full'},

  { path: 'user', component: UsuariosComponent},
  { path: 'user/formUsuario', component: FormUsuariosComponent},
  { path: 'user/formUsuario/:idUsuario', component: FormUsuariosComponent},

  { path: 'compania', component: CompaniaComponent},

  { path: 'roles', component: RolComponent},
  { path: 'perfiles', component: PerfilComponent},
  { path: 'facultades', component: FacultadComponent},
  { path: 'permisoCuentaMonto', component: PermisoCuentaMontoComponent},

  { path: 'graph/:tipo', component: GraphComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
