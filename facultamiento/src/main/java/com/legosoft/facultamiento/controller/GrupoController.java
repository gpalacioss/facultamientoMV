package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping("/saveGrupo")
    public Grupo saveGrupo(@RequestBody Grupo grupo){
        return grupoService.save(grupo);
    }

    @GetMapping(value = "/getGrupoById/{idGrupo}")
    public Grupo getGrupoById(@PathVariable("idGrupo") Long idGrupo){
        return grupoService.findGrupoById(idGrupo);
    }

    @GetMapping(value = "/getGrupoByNombre/{nombreGrupo}")
    public Grupo getGrupoByNombre(@PathVariable("nombreGrupo") String nombreGrupo){
        return grupoService.findGrupoByNombre(nombreGrupo);
    }

}
