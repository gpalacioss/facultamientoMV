import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { UsuarioService } from './service/usuario/usuario.service';
import { HttpClientModule } from '@angular/common/http';
import { PerfilComponent } from './components/perfil/perfil.component';
import { RolComponent } from './components/rol/rol.component';
import { FacultadComponent } from './components/facultad/facultad.component';


@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    PerfilComponent,
    RolComponent,
    FacultadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
