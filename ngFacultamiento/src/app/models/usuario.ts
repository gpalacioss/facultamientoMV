import { Perfil } from 'src/app/models/perfil';
export class Usuario {

  public id: number;

  public idUsuario: number;

  public nombre: String;

  public email: String;

  public telefono: String;

  public isActivo: Boolean;

  public isInterno: Boolean;

  public perfil: Array<Perfil>;

}
