package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.repository.GrupoRepository;
import com.legosoft.facultamiento.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("grupoService")
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo findGrupoById(Long idGrupo){
        return grupoRepository.findByIdGrupo(idGrupo);
    }
    public Grupo save(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public void deleteGrupo(Grupo grupo){
        grupoRepository.delete(grupo);
    }

    public List<Grupo> findAllGrupo(){
       return grupoRepository.findAll();
    }

    public Grupo findGrupoByNombre(String nombre){
        return grupoRepository.findByNombre(nombre);
    }

}
