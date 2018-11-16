package com.legosoft.facultamiento.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.legosoft.facultamiento.models.nuevo.FacultadNM;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	
	@RequestMapping(value = "/infoUsuarios", method = RequestMethod.GET)
	public List<Usuario> infoUsuarios(){
		
		List<Usuario> result = usuarioService.getInfoUsuario("Teresa Toledo Jimenez");
		
		return result.stream().distinct().collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios(){
		
		List<Usuario> result = usuarioService.getAllUsuarios();
		System.out.println("Total de usuarios: " + result.size());
		return result;
	}
	
	@RequestMapping(value = "/findUsuarioByname", method = RequestMethod.GET)
	public List<Usuario> findUsuarioByname(@Param("nombreUsuario") String nombreUsuario){
		List<Usuario> result = usuarioService.findUsuarioBynombre(nombreUsuario);
		return result;
	}
	
	@RequestMapping(value = "getPerfilesGroupByFacultad", method = RequestMethod.GET)
	public  Map<List<String>, Set<String>> getPerfilesGroupByFacultad(){
		List<Perfil> result = perfilService.findAllPerfiles();
		
		@SuppressWarnings("unchecked")
		//devuelve el set de facultades y el set de perfiles unidos  a esas facultades
		Map<List<String>, Set<String>> collect = result.stream().collect(Collectors.groupingBy(Perfil::getNombresFac, Collectors.mapping(Perfil::getNombre, Collectors.toSet())));
		
		Map<Set<Facultad>, Set<String>> collectFacultad = result.stream().collect(Collectors.groupingBy(Perfil::getFacultades, Collectors.mapping(Perfil::getNombre, Collectors.toSet())));
		
		System.out.println("facultades:: " + collect.keySet().size());
		
		collectFacultad.keySet().forEach(f -> {
			
			if (f.size() != 1 || f.size() != 2) {
				//f.forEach(ff -> System.out.println("facultades que solo estan una vez:: " + ff));
				
				f.forEach(ff -> {
					FacultadNM nuevaFacultad = new FacultadNM();
					
					nuevaFacultad.setIdFacultad(ff.getIdFacultad());
				});
			}
			
		});
		
		//Devuelve una lista de nombres de facultades, con un set de perfiles unidos a esas facultades
		Map<List<String>, Set<Perfil>> f = result.stream().collect(Collectors.groupingBy(Perfil::getNombresFac, Collectors.toSet()));
		
		return collect;
	}
	
	

}
