package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.Rol;

import java.util.List;

public interface RolService {
	
	public Rol save(Rol rol);

	public List<Rol> getAllRoles();

	public Rol findRolByNombre(String nombreRol);

	public void deleteRol(Rol rol);


}
