package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.repository.PermisoRepository;
import com.legosoft.facultamiento.repository.FacultadRepository;
import com.legosoft.facultamiento.service.FacultadSerivice;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("facultadService")
public class FacultadSeriviceImpl implements FacultadSerivice {

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    public List<Facultad> findAllFacultad(){
        return  facultadRepository.findAll();
    }

    public Permiso findFacultadNmByIdPermiso(Long idFacultad){
        return permisoRepository.findByIdPermiso(idFacultad);
    }

    public Permiso findByNombrePermiso(String nombrePermiso){
        return permisoRepository.findByNombre(nombrePermiso);
    }


    public Permiso saveOrUpdateFAcultadNuevaMultiva(Permiso permiso){
        return  permisoRepository.save(permiso);
    }

}
