package com.legosoft.facultamiento.service;

import java.util.List;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import com.legosoft.facultamiento.models.old.Perfil;

public interface PerfilService {
	
	public List<Perfil> findAllPerfiles();

	public PerfilNM saveOrUpdate(PerfilNM perfil);

	public List<PerfilNM> getAllperfilesNuevos();

}
