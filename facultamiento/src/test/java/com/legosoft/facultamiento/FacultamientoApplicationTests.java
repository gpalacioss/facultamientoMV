package com.legosoft.facultamiento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.legosoft.facultamiento.models.nuevo.*;
import com.legosoft.facultamiento.repository.PermisoRepository;
import com.legosoft.facultamiento.service.CuentaService;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.PermisoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.internal.compiler.v2_3.ast.QueryTagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.UsuarioService;

import javax.xml.transform.sax.SAXSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacultamientoApplicationTests {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermisoService permisoService;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private CuentaService cuentaService;

    /**
     * @Test public void contextLoads() {
     * }
     * @Test public void getPerfil() {
     * Perfil result = usuarioService.getPerfil();
     * System.out.println("Nombre Perfil:: " + result.getNombre());
     * }
     **/


    @Test
    public void getInfoUsuarios() {
        List<Usuario> result = usuarioService.getInfoUsuario("Teresa Toledo Jimenez");
        result.forEach(u -> {
            System.out.println("Nombre Usuario:: " + u.getNombre());
            u.getPerfiles().forEach(p -> {
                System.out.println("========Perfiles==============");
                System.out.println(p.getNombre());
            });
        });
    }


    @Test
    public void getAllUsuarios() {
		List<Usuario> result = usuarioService.getAllUsuarios();

		result.forEach(u -> {

            System.out.println("El Usuario :: " + u.getNombre() + " Tiene estos numero de cuenta:: " );

//		    if (u.getCuentasBancarias().size() > 0){
//
//                u.getCuentasBancarias().forEach(c -> {
//
//                        CuentaNM nuevaCuenta = cuentaService.findCuentaNMBynumeroCuenta(c.getNumeroCuenta());
//                        if (nuevaCuenta != null){
//                            u.getCuentasBancariasUsuario().add(nuevaCuenta);
//                        }
//		        });
//
//            }

            if (u.getCuentasBancariasUsuario().size() > 0) {
//             usuarioService.saveOrUpdate(u);
            }

        });
    }

    @Test
    public void actualizaRelacion() {

        Permiso permiso = permisoService.findPermisoByNombre("Mul_Con_SaldoPagare");

        permiso.getUsuarioPermisoCuentas().forEach(pc -> {
//            pc.setLimiteMancomunado(new BigDecimal(100));
        });

//        permisoRepository.save(permiso);

    }


    @Test
    public void getUsuarios() {

        List<Usuario> result = usuarioService.findUsuarioBynombre("Teresa Toledo Jimenez");
        System.out.println(result.size());
        result.forEach(u -> {
            System.out.println("======== Info ==============" + u.getPerfiles().size());
            System.out.println("Nombre Usuario:: " + u.getNombre());
            u.getPerfiles().forEach(p -> {

                System.out.println("nombrePerfil" + p.getNombre());

            });
        });
    }


    @Test
    public void RelacionUsuarioPerfil() {

        Optional<Usuario> usuario = usuarioService.findUsuarioById(6L);

        Optional<PerfilNM> perfil = perfilService.findPerfilNMByNombre("perfil-generado-5");

        usuario.ifPresent(usuario1 -> {

           if (perfil.isPresent()){
               usuario1.getPerfiles().add(perfil.get());
           }

            usuario1.getPerfiles().forEach(p -> System.out.println(p.getNombre()));

           //usuarioService.saveOrUpdate(usuario1);

        });
    }


    @Test
    public void permisosConCuentaBancarias(){
        List<Permiso> result = permisoService.findPermisosConCuentas();


        result.forEach(p -> {
            p.getUsuarioPermisoCuentas().forEach(pc -> {
//                UsuarioPermisoCuenta upc = permisoService.findUsuarioPermisoCuentaByCuentaAndPermiso(pc.getCuenta().getNumeroCuenta(), p.getNombre());
//
//                if (upc == null){
//                    upc = new UsuarioPermisoCuenta();
//
////                    upc.setCuenta(pc.getCuenta());
//                    upc.setPermiso(p);
//
//                    //permisoService.saveUsuarioPermisoCuenta(upc);
//
//                }
            });
        });
    }


    @Test
    public void getAllUsuarioPemisoCuentas(){
        List<UsuarioPermisoCuenta> result = permisoService.findAllUsuarioPermisoCuenta();

        for (UsuarioPermisoCuenta upc : result) {

            if (upc.getPermiso() == null || upc.getPermiso().getId() == null){
                permisoService.deleteUsuarioPermisoCuenta(upc);
            }

//            int contador = 0;
//
//            List<Usuario> usuarios = usuarioService.findUsuariosByNumeroCuenta(upc.getCuenta().getNumeroCuenta());
//
//            if (usuarios.size() > 0){
//
//                for (Usuario u : usuarios) {
//
//                    contador ++;
//
//                    UsuarioPermisoCuenta cpu = permisoService.findUsuarioPermisoCuentaByUsuarioAndCuentaAndPermiso(u.getNombre(), upc.getCuenta().getNumeroCuenta(), upc.getPermiso().getNombre());
//
//                    if (cpu == null){
//
//                        UsuarioPermisoCuenta relacion = permisoService.findUsuarioPermisoCuentaByCuentaAndPermiso(upc.getCuenta().getNumeroCuenta(), upc.getPermiso().getNombre());
//
//                        if (contador > 1){
//                            relacion.setId(null);
//
//                        }
//
//
//                        PermisoCuenta pc = new PermisoCuenta();
//
//                        pc.setUsuario(u);
//                        pc.setLimiteInferior(new BigDecimal(100));
//                        pc.setLimiteSuperior(new BigDecimal(10000));
//                        pc.setUsuarioPermisoCuenta(relacion);
//
//
//                        permisoService.save(pc);
//
//                    }
//
//                }
//
//            }

        }

        System.out.println("total de registros" +  result.size());
    }
}
