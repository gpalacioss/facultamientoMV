package com.legosoft.facultamiento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.repository.RolRepository;
import com.legosoft.facultamiento.service.RolService;

@Service("rolService")
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

}
