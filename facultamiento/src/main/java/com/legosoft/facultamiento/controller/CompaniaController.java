package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.service.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CompaniaController {

    @Autowired
    private CompaniaService companiaService;

    @PostMapping(value = "/saveCompania")
    public Compania saveCompania(@RequestBody Compania compania){
        return companiaService.save(compania);
    }

    @GetMapping(value = "/getCompaniaByNombre/{nombreCompania}")
    public Compania getCompaniaByNombreCompania(@PathVariable("nombreCompania") String nombreCompania){
        return companiaService.findCompaniaByNombre(nombreCompania);
    }

    @GetMapping(value = "/getCompaniaById/{idCompania}")
    public Compania getCompaniaByIdCompania(@PathVariable("idCompania") Long idCompania){
        return companiaService.getCompaniaById(idCompania);
    }
}
