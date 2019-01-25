import { Perfil } from 'src/app/models/perfil';
export class Usuario {

  public id: number;

  public idUsuario: number;

  public nombre: String;

  public nombreUsuario: String;

  public email: String;

  public telefono: String;

  public isActivo: Boolean;

  public isInterno: Boolean;

  public isAdministrador: Boolean;

  public perfil: Array<Perfil>;

}
