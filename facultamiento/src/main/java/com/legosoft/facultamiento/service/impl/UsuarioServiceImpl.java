package com.legosoft.facultamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.repository.PerfilRepository;
import com.legosoft.facultamiento.repository.UsuarioRepository;
import com.legosoft.facultamiento.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Usuario> getAllUsuarios(){
		List<Usuario> result = (List<Usuario>) repository.findAll(4);
		return result;
	}

	public List<Usuario> getInfoUsuario(String nombre){
		List<Usuario> result = repository.getInfoUsuario(nombre);
		return result;
	}
	
	public Perfil getPerfil() {
		Perfil result =  perfilRepository.buscaPerfilAndUsario();
		return result;
	}
	
	public List<Usuario> findUsuarioBynombre(String nombre) {
		List<Usuario> result = repository.findByNombre(nombre, 3);
		return result;
	}

}
