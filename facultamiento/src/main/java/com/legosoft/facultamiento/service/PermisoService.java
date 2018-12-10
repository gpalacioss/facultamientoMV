package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;

import java.util.List;

public interface PermisoService {

    public PermisoCuenta findPermisoCuentaByPermisoAndCuenta(String nombrePermiso, String numeroCuenta);

    public PermisoCuenta save(PermisoCuenta permisoCuenta);

    public Permiso findPermisoByNombre(String nombrePermiso);

    public List<Permiso> findPermisosConCuentas();

}
