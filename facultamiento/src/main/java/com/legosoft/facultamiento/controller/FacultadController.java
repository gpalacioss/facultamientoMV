package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.UsuarioPermisoCuenta;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.FacultadSerivice;
import com.legosoft.facultamiento.service.PermisoService;
import com.legosoft.facultamiento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class FacultadController {


    @Autowired
    private FacultadSerivice facultadSerivice;

    @Autowired
    private PermisoService permisoService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/getFacultades", method = RequestMethod.GET)
    public List<Facultad> findAllFacultades(){

        List<Facultad> result = facultadSerivice.findAllFacultad();

        return  result;

    }

    @PostMapping(value = "/savePermiso")
    public Permiso savePermiso(@RequestBody Permiso permiso){
        return permisoService.savePermisoSimple(permiso);
    }

    @PostMapping(value = "/savePermisoCuentaMonto")
    public UsuarioPermisoCuenta savePermisoCuentaMonto(@RequestBody UsuarioPermisoCuenta usuarioPermisoCuenta){
        return permisoService.saveUsuarioPermisoCuenta(usuarioPermisoCuenta);
    }

    @RequestMapping(value = "/getPermisos", method = RequestMethod.GET)
    public List<Permiso> getAllPermisos(){
        List<Permiso> result = facultadSerivice.finAllPermisos();
        return result;
    }

    @RequestMapping(value = "/getPermisosCuentaMonto", method = RequestMethod.GET)
    public List<UsuarioPermisoCuenta> getAllPermisosCuentaMonto(){
        List<UsuarioPermisoCuenta> result = permisoService.findAllUsuarioPermisoCuenta();
        return result;
    }

    @GetMapping(value = "/getPermisosByNombrePerfil/{nombrePerfil}")
    public List<Permiso> findPermisosByNombrePerfil(@PathVariable("nombrePerfil") String nombrePerfil){
        System.out.println(nombrePerfil);
        return permisoService.findPermisoByNombrePerfil(nombrePerfil);
    }

    @GetMapping(value = "/getPermisoByNombre/{nombrePermiso}")
    public Permiso findPermisoByNombre(@PathVariable("nombrePermiso") String nombrePermiso){
        return permisoService.findPermisoByNombre(nombrePermiso);
    }

    @GetMapping(value = "/getPermisoCuentaById/{id}")
    public UsuarioPermisoCuenta getPermisoCuentaMontobyId(@PathVariable("id") Long id){
        return permisoService.findUsuarioPermisoCuentaById(id);
    }

    @PostMapping("/eliminaPermiso")
    public void eliminaPermiso(@RequestBody Permiso permiso){
        Usuario user = usuarioService.findUsuarioBynombre("Zaira Casimiro").stream().findFirst().get();
        user.getPermisosNegados().add(permiso);
        usuarioService.saveOrUpdate(user);
    }

}
