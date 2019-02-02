package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.Compania;

import java.util.List;

public interface CompaniaService {

    public Compania save(Compania compania);

    public Compania getCompaniaById(Long idCompania);

    public void deleteCompania(Compania compania);

    /**
     * servicio que busca  la compania por nombre
     * @param nombreCompania
     * @return
     */
    public Compania findCompaniaByNombre(String nombreCompania);

    /**
     * Servicio que consulta todas las compañias de la tabla
     * @return
     */
    public List<Compania> findAllCompania();
}
