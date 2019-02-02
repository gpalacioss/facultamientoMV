import {Usuario} from './usuario';
import {Permiso} from './permiso';

export class PermisoCuentaMonto {

  public  idUsuarioPermisoCuenta: number;

  public  limiteInferior: string;

  public  limiteSuperior: string;

  public  usuarios: Usuario;

  public  permiso: Permiso;

}
