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
import { FormsModule } from '@angular/forms'


@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    PerfilComponent,
    RolComponent,
    FacultadComponent,
    HeaderComponent,
    FooterComponent,
    FormUsuariosComponent
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
