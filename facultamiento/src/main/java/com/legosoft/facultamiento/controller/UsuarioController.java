package com.legosoft.facultamiento.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

	@GetMapping(value = "/usuariosAndPermisosGraph/{nombreUsuario}")
	public String getUsuariosAndPermisosGraph(@PathVariable("nombreUsuario") String nombreUsuario){

		String json = usuarioService.getUsuarioAndPermisosGraph(nombreUsuario);
		generaArchivoTxt(json, "hola");
		return json;
	}

	private void  generaArchivoTxt(String json, String nombreArchivo){
		try {
			String ruta = "C:\\Users\\Gusstavo\\Documents\\0.4.2\\data/ " + nombreArchivo + ".json";

			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
