package com.legosoft.facultamiento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.repository.PermisoRepository;
import com.legosoft.facultamiento.service.CuentaService;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.PermisoService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
//				p.getFacultades().forEach(f -> {
//					System.out.println("facultades de cada perfil:: " + f.getNombre());
//				});
            });
        });
    }


    @Test
    public void getAllUsuarios() {
		List<Usuario> result = usuarioService.getAllUsuarios();

		result.forEach(u -> {

            System.out.println("El Usuario :: " + u.getNombre() + " Tiene estos numero de cuenta:: " );

		    if (u.getCuentasBancarias().size() > 0){

                u.getCuentasBancarias().forEach(c -> {

                    CuentaNM nuevaCuenta = cuentaService.findCuentaNMBynumeroCuenta(c.getNumeroCuenta());

                    u.getCuentasBancariasUsuario().add(nuevaCuenta);
		        });

            }

            if (u.getCuentasBancariasUsuario().size() > 0) {
                u.getCuentasBancariasUsuario().forEach(cn -> {
                    System.out.println("Cuenta :: " + cn.getNumeroCuenta());
                });
            }

        });
    }

    @Test
    public void actualizaRelacion() {

        Permiso permiso = permisoService.findPermisoByNombre("Mul_Con_SaldoPagare");

        permiso.getLstPermisoCuentas().forEach(pc -> {
            pc.setLimiteMancomunado(new BigDecimal(100));
        });

        permisoRepository.save(permiso);

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

//					p.getFacultades().forEach(f -> System.out.println("nombre facultad :: " + f.getNombre()));
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

           usuarioService.saveOrUpdate(usuario1);

        });
    }


}
