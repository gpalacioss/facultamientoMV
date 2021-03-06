package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.RolService;
import com.legosoft.facultamiento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @PostMapping("/savePerfil")
    public PerfilNM savePerfil(@RequestBody PerfilNM perfilNM){
        return null;
        //return perfilService.saveOrUpdate(perfilNM);
    }

    @RequestMapping(value = "/getPerfiles", method = RequestMethod.GET)
    public List<Perfil> getPerfiles(){
        List<Perfil> result = perfilService.findAllPerfiles();
        return result;
    }

    @RequestMapping(value = "/generaPerfilByRol", method = RequestMethod.GET)
    public void generaPerfilByFacultad(){

        List<Rol> result = rolService.getAllRoles();

        int contador = 1;

        for (Rol r : result) {

            PerfilNM pnm = new PerfilNM();

            pnm.setNombre("perfil-generado-" + contador++);
            pnm.setActivo(Boolean.TRUE);
            pnm.setDescripcion("generando perfiles relacionados este se relaciona con el rol :: " + r.getNombreRol());
            pnm.setFechaCreacion(new Date());
            pnm.setPatron(Boolean.FALSE);
            pnm.setMaestro(Boolean.FALSE);
            pnm.getRoles().add(r);


            System.out.println("PERFIL ===============");
            System.out.println(pnm.getNombre());
            System.out.println(pnm.getFechaCreacion());
            System.out.println(pnm.getDescripcion());
            pnm.getRoles().forEach(rol -> {

                System.out.println("Roles =================");
                System.out.println(r.getIdRol());
                System.out.println(r.getNombreRol());
                System.out.println(r.getFechaCreacion());

                r.getFacultades().forEach(f->{
                    System.out.println("facultad=========== :: " + f.getNombre());
                });

            });


            perfilService.saveOrUpdate(pnm);

        }
    }


    @RequestMapping(value = "/generaRelacionusuarioConNuevosPerfiles", method = RequestMethod.GET)
    public void generaRelacionusuarioConNuevosPerfiles(){
        List<PerfilNM> result = perfilService.getAllperfilesNuevos();

        int contador = 0;
        for (PerfilNM pn : result) {
            contador ++;
            Optional<Usuario> usuario = usuarioService.findUsuarioById(new Long(contador));

            System.out.println("contador:: " + contador);
            usuario.ifPresent(u -> {
                u.getPerfiles().add(pn);
                System.out.println("nombre Usuario:: " + u.getNombre());
                u.getPerfiles().forEach(p -> System.out.println("con el perfil :: " + p.getNombre()));
                usuarioService.saveOrUpdate(u);
            });


        }

    }


    @RequestMapping(value = "/getPerfilesNuevos", method = RequestMethod.GET)
    public List<PerfilNM> getPerfilesNuevos(){

        return  perfilService.getAllperfilesNuevos();
    }

    @GetMapping(value = "/getPerfilByNombre/{nombrePerfil}")
    public PerfilNM getPerfilByNombre(@PathVariable("nombrePerfil") String nombrePerfil){
        return perfilService.findPerfilNMByNombre(nombrePerfil).get();
    }

    @PostMapping(value = "/addPermisos")
    @Consumes("application/json")
    public void addPermisosAndPerfil(@RequestBody Set<Permiso> obj){
       Usuario user =  usuarioService.findUsuarioBynombre("Zaira Casimiro").stream().findFirst().get();
       user.setPermisosNegados(obj);
       usuarioService.saveOrUpdate(user);
        System.out.println(obj.size());
    }

    @PostMapping(value = "/addPerfil")
    public void addPermisosAndPerfil(@RequestBody PerfilNM perfil){
        Usuario user =  usuarioService.findUsuarioBynombre("Zaira Casimiro").stream().findFirst().get();
        user.getPerfiles().add(perfil);
        usuarioService.saveOrUpdate(user);
    }
}
