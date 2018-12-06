package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import com.legosoft.facultamiento.repository.PermisoCuentaRepository;
import com.legosoft.facultamiento.repository.PermisoRepository;
import com.legosoft.facultamiento.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("permisoService")
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    private PermisoCuentaRepository permisoCuentaRepository;

    @Autowired
    private PermisoRepository permisoRepository;


    public PermisoCuenta findPermisoCuentaByPermisoAndCuenta(String nombrePermiso, String numeroCuenta){
        return  permisoCuentaRepository.findByPermisoAndCuenta(nombrePermiso, numeroCuenta);
    }

    @Transactional
    public PermisoCuenta save(PermisoCuenta permisoCuenta){

        return permisoCuentaRepository.save(permisoCuenta, 0);
    }

    public Permiso findPermisoByNombre(String nombrePermiso){
        return permisoRepository.findByNombre(nombrePermiso);
    }
}