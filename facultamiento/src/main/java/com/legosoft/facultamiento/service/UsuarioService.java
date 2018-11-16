package com.legosoft.facultamiento.service;

import java.util.List;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;

public interface UsuarioService {
	
	public List<Usuario> getAllUsuarios();
	
	public List<Usuario> findUsuarioBynombre(String nombre);
	
	public List<Usuario> getInfoUsuario(String nombre);
	
	public Perfil getPerfil();

}
