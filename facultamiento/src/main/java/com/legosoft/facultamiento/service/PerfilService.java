package com.legosoft.facultamiento.service;

import java.util.List;
import java.util.Optional;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import com.legosoft.facultamiento.models.old.Perfil;

public interface PerfilService {
	
	public List<Perfil> findAllPerfiles();

	public PerfilNM saveOrUpdate(PerfilNM perfil);

	public List<PerfilNM> getAllperfilesNuevos();

	public Optional<PerfilNM> findPerfilNuevoById(Long idPerfilNuevo);

	public Optional<PerfilNM> findPerfilNMByNombre(String nombre);

}
