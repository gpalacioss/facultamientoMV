import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { HttpClientModule } from '@angular/common/http';
import { PerfilComponent } from './components/perfil/perfil.component';
import { RolComponent } from './components/rol/rol.component';
import { FacultadComponent } from './components/facultad/facultad.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormUsuariosComponent } from './components/usuarios/form-usuarios/form-usuarios.component';
import { FormsModule } from '@angular/forms';
import { ComboBoxPerfilComponent } from './components/perfil/combo-box-perfil/combo-box-perfil.component';
import { GraphComponent } from './components/graph/graph.component';
import { PermisoCuentaMontoComponent } from './components/permiso-cuenta-monto/permiso-cuenta-monto.component';


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
    PermisoCuentaMontoComponent
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
