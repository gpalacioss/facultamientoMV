package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.service.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CompaniaController {

    @Autowired
    private CompaniaService companiaService;

    @GetMapping(value = "/getCompaniaByNombre/{nombreCompania}")
    public Compania getCompaniaByNombreCompania(@PathVariable("nombreCompania") String nombreCompania){
        return companiaService.findCompaniaByNombre(nombreCompania);
    }
}
