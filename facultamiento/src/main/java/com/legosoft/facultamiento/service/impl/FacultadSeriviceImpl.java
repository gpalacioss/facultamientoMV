package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.FacultadNM;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.repository.FacultadNMRepository;
import com.legosoft.facultamiento.repository.FacultadRepository;
import com.legosoft.facultamiento.service.FacultadSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("facultadService")
public class FacultadSeriviceImpl implements FacultadSerivice {

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private FacultadNMRepository facultadNMRepository;

    public List<Facultad> findAllFacultad(){
        return  facultadRepository.findAll();
    }

    public FacultadNM findFacultadNmByIdFacultad(Long idFacultad){
        return facultadNMRepository.findByIdFacultad(idFacultad);
    }


    public FacultadNM saveOrUpdateFAcultadNuevaMultiva(FacultadNM facultad){
        return  facultadNMRepository.save(facultad);
    }

}
