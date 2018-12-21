package com.legosoft.facultamiento.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.legosoft.facultamiento.service.*;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.legosoft.facultamiento.models.old.Usuario;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private RolService rolService;

	@Autowired
    private CuentaService cuentaService;

	@Autowired
	private FacultadSerivice facultadSerivice;

	@RequestMapping(value = "/infoUsuarios", method = RequestMethod.GET)
	public List<Usuario> infoUsuarios() {

		List<Usuario> result = usuarioService.getInfoUsuario("Teresa Toledo Jimenez");

		return result.stream().distinct().collect(Collectors.toList());
	}

	@RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios() {

		List<Usuario> result = usuarioService.getAllUsuarios();
		System.out.println("Total de usuarios: " + result.size());
		return result;
	}

	@RequestMapping(value = "/findUsuarioByname", method = RequestMethod.GET)
	public List<Usuario> findUsuarioByname(@Param("nombreUsuario") String nombreUsuario) {
		List<Usuario> result = usuarioService.findUsuarioBynombre(nombreUsuario);
		return result;
	}

	@PostMapping(value = "/saveOrUpdateUsuario")
	public Usuario saveOrUpdateUsuario(@RequestBody Usuario usuario){

		return  usuarioService.saveOrUpdate(usuario);
	}

	@GetMapping(value = "/getUsuarios/{idUsuario}")
	public Optional<Usuario> getUsuarioById(@PathVariable("idUsuario") Long idUsuario){
		return usuarioService.findUsuarioById(idUsuario);
	}

	@GetMapping(value = "/usuariosAndPermisosGraph")
	public String getUsuariosAndPermisosGraph(@Param("nombreUsuario") String nombreUsuario){
		return null;
	}
}
