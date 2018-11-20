import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { RolComponent } from './components/rol/rol.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { FacultadComponent } from './components/facultad/facultad.component';

const routes: Routes = [
  { path: '', redirectTo: '/appComponent', pathMatch: 'full'},

  /**
   * redirecciona al appComponent que es la ruta principal
   */
  { path: 'appComponent', component: AppComponent},
  { path: 'user', component: UsuariosComponent},
  { path: 'roles', component: RolComponent},
  { path: 'perfiles', component: PerfilComponent},
  { path: 'facultades', component: FacultadComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
