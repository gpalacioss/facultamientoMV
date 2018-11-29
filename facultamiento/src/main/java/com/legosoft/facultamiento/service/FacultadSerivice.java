package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.old.Facultad;

import java.util.List;

public interface FacultadSerivice {

    public List<Facultad> findAllFacultad();

    public Permiso findFacultadNmByIdPermiso(Long idPermiso);

    public Permiso findByNombrePermiso(String nombrePermiso);

    public Permiso saveOrUpdateFAcultadNuevaMultiva(Permiso permiso);
}
