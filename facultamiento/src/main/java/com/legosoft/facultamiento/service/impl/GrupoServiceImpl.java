package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.repository.GrupoRepository;
import com.legosoft.facultamiento.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;

public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;


    public Grupo save(Grupo grupo){
        return grupoRepository.save(grupo);
    }


}
