package com.legosoft.facultamiento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.repository.RolRepository;
import com.legosoft.facultamiento.service.RolService;

import java.util.List;

@Service("rolService")
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

	public List<Rol> getAllRoles(){
		return rolRepository.findAll();
	}

	public Rol findRolByNombre(String nombreRol){
		return rolRepository.findByNombreRol(nombreRol);
	}

	public void deleteRol(Rol rol){
		rolRepository.delete(rol);
	}
}
