package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.service.FacultadSerivice;
import com.legosoft.facultamiento.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class FacultadController {


    @Autowired
    private FacultadSerivice facultadSerivice;

    @Autowired
    private PermisoService permisoService;

    @RequestMapping(value = "/getFacultades", method = RequestMethod.GET)
    public List<Facultad> findAllFacultades(){

        List<Facultad> result = facultadSerivice.findAllFacultad();

        return  result;

    }


    @RequestMapping(value = "/getPermisos", method = RequestMethod.GET)
    public List<Permiso> getAllPermisos(){
        return facultadSerivice.finAllPermisos();
    }

    @GetMapping(value = "/getPermisos/{nombrePerfil}")
    public List<Permiso> findPermisosByNombrePerfil(@PathVariable("nombrePerfil") String nombrePerfil){
        System.out.println(nombrePerfil);
        return permisoService.findPermisoByNombrePerfil(nombrePerfil);
    }

}
