import {Usuario} from './usuario';
import {Permiso} from './Permiso';

export class PermisoCuentaMonto {


  public  idUsuarioPermisoCuenta: number;

  public  limiteInferior: String;

  public  limiteSuperior: String;

  public  usuarios: Usuario;

  public  permiso: Permiso;

}
