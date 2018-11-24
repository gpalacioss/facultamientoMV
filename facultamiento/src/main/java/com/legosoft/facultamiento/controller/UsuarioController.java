package com.legosoft.facultamiento.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.legosoft.facultamiento.models.nuevo.FacultadNM;
import com.legosoft.facultamiento.repository.FacultadRepository;
import com.legosoft.facultamiento.service.FacultadSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.RolService;
import com.legosoft.facultamiento.service.UsuarioService;

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

	@RequestMapping(value = "getPerfilesGroupByFacultad", method = RequestMethod.GET)
	public Map<List<String>, Set<String>> getPerfilesGroupByFacultad() {
		List<Perfil> result = perfilService.findAllPerfiles();

		@SuppressWarnings("unchecked")
		// devuelve el set de facultades y el set de perfiles unidos a esas facultades
		Map<List<String>, Set<String>> collect = result.stream().collect(Collectors.groupingBy(Perfil::getNombresFac,
				Collectors.mapping(Perfil::getNombre, Collectors.toSet())));

		Map<Set<Facultad>, Set<String>> collectFacultad = result.stream().collect(Collectors
				.groupingBy(Perfil::getFacultades, Collectors.mapping(Perfil::getNombre, Collectors.toSet())));

		System.out.println("facultades:: " + collect.keySet().size());

		int contador = 1;
		for (Set<Facultad> f : collectFacultad.keySet()) {
			

			if (f.size() >= 3) {
				
				Rol rol = new Rol();
				
				rol.setFechaCreacion(new Date());
				rol.setIsActivo(Boolean.TRUE);
				rol.setNombreRol("Rol:" + contador++);

				f.forEach(ff -> {

					FacultadNM nf = facultadSerivice.findFacultadNmByIdFacultad(Long.parseLong(ff.getIdFacultad()));

					if (nf == null){

						nf = new FacultadNM();

						nf.setActivo(ff.isActivo());
						nf.setDescripcion(ff.getDescripcion());
						nf.setFechaModificacion(ff.getFechaModificacion());
						nf.setHoraFinal(ff.getHoraFinal());
						nf.setHoraInicio(ff.getHoraInicio());
						nf.setIdFacultad(Long.parseLong(ff.getIdFacultad()));
						nf.setIsRestriccionHorario(ff.isRestriccionHorario());
						nf.setNombre(ff.getNombre());
						nf.setTipoFacultad(ff.getTipoFacultad());

						nf = facultadSerivice.saveOrUpdateFAcultadNuevaMultiva(nf);
					}

					rol.getFacultades().add(nf);
					System.out.println("El rol : " + rol.getNombreRol() + " Contiene las siguientes facultades:: ");
					System.out.println("facultad :: " + nf.getNombre());

				});
				
				Rol r = rolService.save(rol);
			}

		}

		return null;
	}

}
