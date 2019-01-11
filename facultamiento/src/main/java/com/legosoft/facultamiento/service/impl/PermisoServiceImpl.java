package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import com.legosoft.facultamiento.models.nuevo.UsuarioPermisoCuenta;
import com.legosoft.facultamiento.repository.PermisoCuentaRepository;
import com.legosoft.facultamiento.repository.PermisoRepository;
import com.legosoft.facultamiento.repository.UsuarioPermisoCuentaRepository;
import com.legosoft.facultamiento.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("permisoService")
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    private PermisoCuentaRepository permisoCuentaRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private UsuarioPermisoCuentaRepository usuarioPermisoCuentaRepository;


    public PermisoCuenta findPermisoCuentaByPermisoAndCuenta(String nombrePermiso){
        return  permisoCuentaRepository.findByPermisoAndCuenta(nombrePermiso);
    }

    @Transactional
    public PermisoCuenta save(PermisoCuenta permisoCuenta){

        return permisoCuentaRepository.save(permisoCuenta, 0);
    }

    public Permiso savePermisoSimple(Permiso permiso){

        return permisoRepository.save(permiso);
    }

    public Permiso findPermisoByNombre(String nombrePermiso){
        return permisoRepository.findByNombre(nombrePermiso);
    }

    public List<Permiso> findPermisosConCuentas(){
        return permisoRepository.findPermisoConCuentas();
    }

    public UsuarioPermisoCuenta findUsuarioPermisoCuentaByUsuarioAndCuentaAndPermiso(String usuario, String numeroCuenta, String nombrePermiso){
        return usuarioPermisoCuentaRepository.findUsuarioPermisoCuentaByusuarioAndPermisoAndCuenta(usuario, numeroCuenta, nombrePermiso);
    }

    public UsuarioPermisoCuenta findUsuarioPermisoCuentaByCuentaAndPermiso(String numeroCuenta, String nombrePermiso){
        return usuarioPermisoCuentaRepository.findUsuarioPermisoCuentaByPermisoAndCuenta(numeroCuenta, nombrePermiso);
    }

    public UsuarioPermisoCuenta saveUsuarioPermisoCuenta(UsuarioPermisoCuenta usuarioPermisoCuenta){
        return usuarioPermisoCuentaRepository.save(usuarioPermisoCuenta);
    }

    public List<UsuarioPermisoCuenta> findAllUsuarioPermisoCuenta(){
        return usuarioPermisoCuentaRepository.findAll();
    }

    public void deleteUsuarioPermisoCuenta(UsuarioPermisoCuenta upc){
        usuarioPermisoCuentaRepository.delete(upc);
    }

    public Optional<PermisoCuenta> findPermisoCuentaById(Long id){
        return permisoCuentaRepository.findById(id);
    }
}
