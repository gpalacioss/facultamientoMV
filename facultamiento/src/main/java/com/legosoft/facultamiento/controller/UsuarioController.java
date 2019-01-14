package com.legosoft.facultamiento.controller;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.legosoft.facultamiento.models.nuevo.*;
import com.legosoft.facultamiento.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.legosoft.facultamiento.models.old.Usuario;


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
	private Object HttpStatusCodeException;

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
		System.out.println(nombreUsuario);
		String json = usuarioService.getPermisosCuentaMontoByUsuario(nombreUsuario);
		return json;
	}

	@GetMapping(value = "/getPermisosCuentaMontoAndSimplesByUsuario/{nombreUsuario}")
	public String getPermisosCuentaMontoAndSimplesByUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
		String json = usuarioService.getPermisosCuentaMontoAndSimplesByUsuario(nombreUsuario);
		return json;
	}

	@PostMapping(value = "/eliminaNodo")
	public Response eliminaNodo(@RequestBody String info){

		String[] datos = info.split("&");
		String[] nombre = datos[0].split("=");
		String[] tipo = datos[1].split("=");
		String[] nombreUsuario = datos[2].split("=");
        String[] id = datos[3].split("=");
		System.out.println(nombre[1].replaceAll("\\+", " ") + " " + tipo[1]);

		Usuario usuario = usuarioService.findUsuarioBynombre(nombreUsuario[1].replaceAll("\\+", " ")).stream().findFirst().get();

		String nm = nombre[1].replaceAll("\\+", " ");
		Response response = null;
		switch (tipo[1]){


			case "usuario":
				Usuario user = usuarioService.findUsuarioBynombre(nm).stream().findFirst().get();

				if (user != null && user.getUsuarioPermisoCuentas().size() == 0 && user.getCuentasBancariasUsuario().size() == 0 && user.getPermisoAgregados().size() == 0 && user.getPerfiles().size() == 0){
                    usuarioService.deleteUsuario(user);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
                }
				break;

			case "grupo":
				Grupo grupo = grupoService.findGrupoByNombre(nm);

				if (grupo != null && grupo.getCompaniasPermitidasSinHerencia().size() == 0 && grupo.getCompanias().size() == 0){
                    grupoService.deleteGrupo(grupo);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}
				break;

			case "compania":
				Compania compania = companiaService.findCompaniaByNombre(nm);

				if (compania != null && compania.getCuentasEmpresas().size() == 0 &&  compania.getUsuarios().size() == 0 && compania.getCompaniaHijo().size() == 0){
                    companiaService.deleteCompania(compania);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}
				break;

			case "rol":
				Rol rol = rolService.findRolByNombre(nm);

				if (rol != null && rol.getFacultades().size() == 0){
                    rolService.deleteRol(rol);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}
				break;

			case "perfil":

				Optional<PerfilNM> perfil = perfilService.findPerfilNMByNombre(nm);

				if (perfil.isPresent() && perfil.get().getRoles().size() == 0){
                    perfilService.deletePerfilNuevo(perfil.get());
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}

				break;

			case "cuenta":
				CuentaNM cuenta = cuentaService.findCuentaNMBynumeroCuenta(nm);

				if (cuenta != null && cuenta.getUsuarioPermisoCuentas().size() == 0){
					cuentaService.deleteCuenta(cuenta);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}
				break;

			case "permiso":
				Permiso permiso = permisoService.findPermisoByNombre(nm);


				if (permiso != null && usuario != null ){
					usuario.getPermisosNegados().add(permiso);
                    usuarioService.saveOrUpdate(usuario);
				}else{

					response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
				}
				break;

            case "permisoCuentaMonto":
                UsuarioPermisoCuenta upc = permisoService.findUsuarioPermisoCuentaById(Long.parseLong(id[1]));


                if (upc != null && usuario != null ){
                    permisoService.deleteUsuarioPermisoCuenta(upc);
                }else{

                    response = new Response(HttpStatus.NOT_MODIFIED.value(), "No se puede eliminar por que tiene informacion relacionada");
                }
                break;


		}
		return response;
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
				UsuarioPermisoCuenta permisoCuenta = permisoService.findUsuarioPermisoCuentaById(Long.parseLong(param[3]));

				if (permisoCuenta != null){

					permisoCuenta.setLimiteInferior(new BigDecimal(param[1]));
					permisoCuenta.setLimiteSuperior(new BigDecimal(param[4]));

					permisoService.saveUsuarioPermisoCuenta(permisoCuenta);
				}

				break;
		}

	}

}
