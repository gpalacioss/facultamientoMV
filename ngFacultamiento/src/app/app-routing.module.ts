import { PermisosUsuarioComponent } from './components/usuarios/permisos-usuario/permisos-usuario.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { RolComponent } from './components/rol/rol.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { FacultadComponent } from './components/facultad/facultad.component';
import { FormUsuariosComponent } from './components/usuarios/form-usuarios/form-usuarios.component';

const routes: Routes = [
  { path: ' ', redirectTo: '/appComponent', pathMatch: 'full'},
  { path: 'user', component: UsuariosComponent},
  { path: 'roles', component: RolComponent},
  { path: 'perfiles', component: PerfilComponent},
  { path: 'facultades', component: FacultadComponent},
  { path: 'user/formUsuario', component: FormUsuariosComponent},
  { path: 'user/formUsuario/actualizaUsuario/:idUsuario', component: FormUsuariosComponent},
  { path: 'user/graph/permisoUsuario', component: PermisosUsuarioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
