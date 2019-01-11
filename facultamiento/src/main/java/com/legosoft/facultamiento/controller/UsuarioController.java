package com.legosoft.facultamiento.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legosoft.facultamiento.models.nuevo.*;
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
		System.out.println(nombre[1].replaceAll("\\+", " ") + " " + tipo[1]);


		String nm = nombre[1].replaceAll("\\+", " ");
		switch (tipo[1]){

			case "usuario":
				Usuario user = usuarioService.findUsuarioBynombre(nm).stream().findFirst().get();

				if (user != null ){

				}
				break;

			case "grupo":
				Grupo grupo = grupoService.findGrupoByNombre(nm);

				if (grupo != null ){

				}
				break;

			case "compania":
				Compania compania = companiaService.findCompaniaByNombre(nm);

				if (compania != null ){

				}
				break;

			case "rol":
				Rol rol = rolService.findRolByNombre(nm);

				if (rol != null ){

				}
				break;
			case "perfil":

				Optional<PerfilNM> perfil = perfilService.findPerfilNMByNombre(nm);

				if (perfil.isPresent() && perfil.get().getRoles().size() != 0){

				}
				break;

			case "cuenta":
				CuentaNM cuenta = cuentaService.findCuentaNMBynumeroCuenta(nm);

				if (cuenta != null && cuenta.getLstPermisoCuentas().size() != 0 && cuenta.getUsuarioPermisoCuentas().size() != 0){
					cuentaService.deleteCuenta(cuenta);
				}else {
					System.out.println("no se puede eliminar por que tienen nodos relacionados");
				}

				break;
			case "permiso":
				Permiso permiso = permisoService.findPermisoByNombre(nm);

				if (permiso != null ){

				}
				break;

		}

	}

	@PostMapping("/editaNodo")
	public void editaNodo(@RequestBody String info){

		System.out.println(info);

		String[] datos = info.split("&");

		String[] param = new String[]{"","","","",""};
		int contador = 0;
		for (String dato : datos) {

			String[] d = dato.split("=");
			param[contador] = d[1].replaceAll("\\+", " ").replaceAll("%3A", ":").replaceAll("%2520", " ");
			contador++;
		}

		System.out.println(param[0]);
		System.out.println(param[1]);
		System.out.println(param[2]);

		switch (param[2]){
			case "usuario":
				Usuario user = usuarioService.findUsuarioBynombre(param[0]).stream().findFirst().get();

				if (user != null ){
					user.setNombre(param[1]);
					usuarioService.saveOrUpdate(user);
				}
				break;

			case "grupo":
				Grupo grupo = grupoService.findGrupoByNombre(param[0]);

				if (grupo != null ){
					grupo.setNombre(param[1]);
					grupoService.save(grupo);
				}
				break;

			case "compania":
				Compania compania = companiaService.findCompaniaByNombre(param[0]);

				if (compania != null ){
					compania.setNombreCompania(param[1]);
					companiaService.save(compania);
				}
				break;

			case "rol":
				Rol rol = rolService.findRolByNombre(param[0]);

				if (rol != null ){
					rol.setNombreRol(param[1]);
					rolService.save(rol);
				}
				break;
			case "perfil":

				Optional<PerfilNM> perfil = perfilService.findPerfilNMByNombre(param[0]);

				if (perfil.isPresent()){
					PerfilNM pr = perfil.get();
					pr.setNombre(param[1]);
					perfilService.saveOrUpdate(pr);
				}
				break;
			case "cuenta":
				CuentaNM cuenta = cuentaService.findCuentaNMBynumeroCuenta(param[0]);

				if (cuenta != null ){
					cuenta.setNumeroCuenta(param[1]);
					cuentaService.save(cuenta);
				}
				break;
			case "permiso":
				Permiso permiso = permisoService.findPermisoByNombre(param[0]);

				if (permiso != null ){
					permiso.setNombre(param[1]);
					permisoService.savePermisoSimple(permiso);
				}
				break;

			case "permisoCuentaMonto":
				Optional<PermisoCuenta> permisoCuenta = permisoService.findPermisoCuentaById(Long.parseLong(param[3]));

				if (permisoCuenta.isPresent()){
					PermisoCuenta pc = permisoCuenta.get();

					pc.setLimiteInferior(new BigDecimal(param[1]));
					pc.setLimiteSuperior(new BigDecimal(param[4]));

					permisoService.save(pc);
				}

				break;
		}

	}

}
