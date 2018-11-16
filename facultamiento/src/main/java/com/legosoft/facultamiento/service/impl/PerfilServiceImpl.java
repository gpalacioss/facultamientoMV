package com.legosoft.facultamiento.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.repository.PerfilRepository;
import com.legosoft.facultamiento.service.PerfilService;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	
	public List<Perfil> findAllPerfiles(){
		
		List<Perfil> result = (List<Perfil>) perfilRepository.findAll();
		
		return result;
		
	}

}
