package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.service.FacultadSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class FacultadController {


    @Autowired
    private FacultadSerivice facultadSerivice;

    @RequestMapping(value = "/getFacultades", method = RequestMethod.GET)
    public List<Facultad> findAllFacultades(){

        List<Facultad> result = facultadSerivice.findAllFacultad();

        return  result;

    }


    @GetMapping(value = "/getPermisos")
    public List<Permiso> getAllPermisos(){
        return facultadSerivice.finAllPermisos();
    }
}
