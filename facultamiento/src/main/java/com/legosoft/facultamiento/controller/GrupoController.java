package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping(value = "/getGrupoByNombre/{nombreGrupo}")
    public Grupo getGrupoByNombre(@PathVariable("nombreGrupo") String nombreGrupo){
        return grupoService.findGrupoByNombre(nombreGrupo);
    }

}
