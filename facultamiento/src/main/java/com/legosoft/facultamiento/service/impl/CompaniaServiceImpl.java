package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.repository.CompaniaRepository;
import com.legosoft.facultamiento.service.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companiaService")
public class CompaniaServiceImpl implements CompaniaService {

    @Autowired
    private CompaniaRepository companiaRepository;


    public Compania save(Compania compania){

        return companiaRepository.save(compania);
    }

    public Compania findCompaniaByNombre(String nombreCompania){
        return companiaRepository.findCompaniaByNombreCompania(nombreCompania);
    }

    public List<Compania> findAllCompania(){
        return companiaRepository.findAll();
    }
}
