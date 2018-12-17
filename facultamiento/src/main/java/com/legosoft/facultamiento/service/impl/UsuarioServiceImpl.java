package com.legosoft.facultamiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<Usuario> result = (List<Usuario>) repository.findAll();
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
		List<Usuario> result = repository.findByNombre(nombre);
		return result;
	}
	
	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public Optional<Usuario> findUsuarioById(Long id){
		return repository.findById(id);
	}

	public Usuario saveOrUpdate(Usuario usuario){
		return repository.save(usuario);
	}

	public List<Usuario> findUsuariosByNumeroCuenta(String numeroCuenta){
		return repository.findusuariosByNumeroCuenta(numeroCuenta);
	}

}
