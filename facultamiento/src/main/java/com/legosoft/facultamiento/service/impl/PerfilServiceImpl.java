package com.legosoft.facultamiento.service.impl;


import java.util.List;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import com.legosoft.facultamiento.repository.PerfilNMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.repository.PerfilRepository;
import com.legosoft.facultamiento.service.PerfilService;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private PerfilNMRepository perfilNMRepository;
	
	
	public List<Perfil> findAllPerfiles(){
		
		List<Perfil> result = (List<Perfil>) perfilRepository.findAll(4);
		
		return result;
		
	}

	public PerfilNM saveOrUpdate(PerfilNM perfil){
		return  perfilNMRepository.save(perfil);
	}

	public List<PerfilNM> getAllperfilesNuevos(){
		return perfilNMRepository.findAll();
	}

}
