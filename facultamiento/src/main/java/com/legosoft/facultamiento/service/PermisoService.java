package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import com.legosoft.facultamiento.models.nuevo.UsuarioPermisoCuenta;

import java.util.List;

public interface PermisoService {

    public PermisoCuenta findPermisoCuentaByPermisoAndCuenta(String nombrePermiso);

    public PermisoCuenta save(PermisoCuenta permisoCuenta);

    public Permiso findPermisoByNombre(String nombrePermiso);

    public List<Permiso> findPermisosConCuentas();

    public UsuarioPermisoCuenta findUsuarioPermisoCuentaByUsuarioAndCuentaAndPermiso(String usuario, String numeroCuenta, String nombrePermiso);

    public UsuarioPermisoCuenta findUsuarioPermisoCuentaByCuentaAndPermiso(String numeroCuenta, String nombrePermiso);

    public UsuarioPermisoCuenta saveUsuarioPermisoCuenta(UsuarioPermisoCuenta usuarioPermisoCuenta);

    public List<UsuarioPermisoCuenta> findAllUsuarioPermisoCuenta();

    public void deleteUsuarioPermisoCuenta(UsuarioPermisoCuenta upc);

}
