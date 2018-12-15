package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.Grupo;

import java.util.List;

public interface GrupoService {

    public Grupo save(Grupo grupo);

    /**
     * Servicio que regresa todos los grupos existentes
     * @return
     */
    public List<Grupo> findAllGrupo();
}
