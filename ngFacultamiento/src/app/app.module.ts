import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { HttpClientModule } from '@angular/common/http';
import { PerfilComponent } from './components/perfil/perfil.component';
import { RolComponent } from './components/rol/rol.component';
import { FacultadComponent } from './components/permiso/facultad.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormUsuariosComponent } from './components/usuarios/form-usuarios/form-usuarios.component';
import { FormsModule } from '@angular/forms';
import { ComboBoxPerfilComponent } from './components/perfil/combo-box-perfil/combo-box-perfil.component';
import { GraphComponent } from './components/graph/graph.component';
import { PermisoCuentaMontoComponent } from './components/permiso-cuenta-monto/permiso-cuenta-monto.component';
import { FormRolComponent } from './components/rol/form-rol/form-rol.component';
import { FormPerfilComponent } from './components/perfil/form-perfil/form-perfil.component';
import { CompaniaComponent } from './components/compania/compania.component';
import { GrupoComponent } from './components/grupo/grupo.component';
import { CuentaComponent } from './components/cuenta/cuenta.component';
import { FormCompaniaComponent } from './components/compania/form-compania/form-compania.component';
import {FormCuentaComponent} from './components/cuenta/form-cuenta/form-cuenta.component';
import { FormGrupoComponent } from './components/grupo/form-grupo/form-grupo.component';
import { FormPermisoComponent } from './components/permiso/form-permiso/form-permiso.component';
import { FormPermisoCuentaComponent } from './components/permiso-cuenta-monto/form-permiso-cuenta/form-permiso-cuenta.component';


@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    PerfilComponent,
    RolComponent,
    FacultadComponent,
    HeaderComponent,
    FooterComponent,
    FormUsuariosComponent,
    ComboBoxPerfilComponent,
    GraphComponent,
    PermisoCuentaMontoComponent,
    FormRolComponent,
    FormPerfilComponent,
    CompaniaComponent,
    GrupoComponent,
    CuentaComponent,
    FormCompaniaComponent,
    FormCuentaComponent,
    FormGrupoComponent,
    FormPermisoComponent,
    FormPermisoCuentaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
