package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.FacultadNM;
import com.legosoft.facultamiento.models.old.Facultad;

import java.util.List;

public interface FacultadSerivice {

    public List<Facultad> findAllFacultad();

    public FacultadNM findFacultadNmByIdFacultad(Long idFacultad);

    public FacultadNM saveOrUpdateFAcultadNuevaMultiva(FacultadNM facultad);
}
