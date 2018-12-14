package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.repository.CompaniaRepository;
import com.legosoft.facultamiento.service.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompaniaServiceImpl implements CompaniaService {

    @Autowired
    private CompaniaRepository companiaRepository;


    public Compania save(Compania compania){

        return companiaRepository.save(compania);
    }
}
