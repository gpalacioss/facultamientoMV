package com.legosoft.facultamiento.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.service.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.legosoft.facultamiento.models.old.Usuario;

import javax.websocket.server.PathParam;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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

	@Autowired
	private PermisoService permisoService;

	@Autowired
	private CompaniaService companiaService;

	@Autowired
	private GrupoService grupoService;

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

//	@RequestMapping(value = "/findUsuarioByname", method = RequestMethod.GET)
//	public List<Usuario> findUsuarioByname(@Param("nombreUsuario") String nombreUsuario) {
//		List<Usuario> result = usuarioService.findUsuarioBynombre(nombreUsuario);
//		return result;
//	}

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
		return json;
	}

	@GetMapping(value = "/getEmpresasbyAdministradorGraph/{nombreUsuario}")
	public String getEmpresasbyAdministradorGraph(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getEmpresasbyAdministradorGraph(nombreUsuario);
		return json;
	}

	@GetMapping(value = "/getEmpresaAndEmpleadosByAdministrador/{nombreUsuario}")
	public String getEmpresaAndEmpleadosByAdministrador(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getEmpresaAndEmpleadosByAdministrador(nombreUsuario);
		return json;
	}

	@GetMapping(value = "/getCuentasEmpresasByAdministrador/{nombreUsuario}")
	public String getCuentasEmpresasByAdministrador(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getCuentasEmpresasByAdministrador(nombreUsuario);
		return json;
	}

	@GetMapping(value = "/getPermisosCuentaMontoByUsuario/{nombreUsuario}")
	public String getPermisosCuentaMontoByUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getPermisosCuentaMontoByUsuario(nombreUsuario);
		return json;
	}

	@GetMapping(value = "/getPermisosCuentaMontoAndSimplesByUsuario/{nombreUsuario}")
	public String getPermisosCuentaMontoAndSimplesByUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getPermisosCuentaMontoAndSimplesByUsuario(nombreUsuario);
		return json;
	}

	@PostMapping(value = "/eliminaNodo")
	public void eliminaNodo(@RequestBody String info){

		String[] datos = info.split("&");
		String[] nombre = datos[0].split("=");
		String[] tipo = datos[1].split("=");
		System.out.println(nombre[1].replaceAll("\\+", " ") + "" + tipo[1]);


		String nm = nombre[1].replaceAll("\\+", " ");
		switch (tipo[1]){

			case "usuario":
					Usuario user = usuarioService.findUsuarioBynombre(nm).stream().findFirst().get();

					if (user != null ){
						System.out.println("elimina usuario :: " + user.getNombre());
					}
				break;

			case "grupo":
				Grupo grupo = grupoService.findGrupoByNombre(nm);

				if (grupo != null ){
					System.out.println("elimina grupo:: " + grupo.getNombre());
				}
				break;

			case "compania":
				Compania compania = companiaService.findCompaniaByNombre(nm);

				if (compania != null ){
					System.out.println("elimina compañia :: " + compania.getNombreCompania());
				}
				break;
		}

	}

	@PostMapping("/editaNodo")
	public void editaNodo(@RequestBody String info){

	}

}
