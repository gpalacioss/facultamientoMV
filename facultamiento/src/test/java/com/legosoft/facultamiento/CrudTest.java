package com.legosoft.facultamiento;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.CompaniaService;
import com.legosoft.facultamiento.service.GrupoService;
import com.legosoft.facultamiento.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

    @Autowired
    private CompaniaService companiaService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void save(){

        Compania compania = companiaService.findCompaniaByNombre("Unifin");

        if (compania == null){

            compania =  new Compania();

            compania.setNombreCompania("Unifin");
            compania.setPadre(true);
            companiaService.save(compania);

        }else{
            System.out.println("La compañia ya existe");
        }


    }


    @Test
    public void creaRelacionCompaniaCompania(){

        Compania compania = companiaService.findCompaniaByNombre("Legolock");
        Compania companiaPadre = companiaService.findCompaniaByNombre("Legosoft");

        compania.setPadre(false);

        companiaPadre.getCompaniaHijo().add(compania);

        companiaService.save(companiaPadre);

    }


    @Test
    public void getAllGrupos(){
        List<Grupo> result = grupoService.findAllGrupo();

        Compania companiaPadre = companiaService.findCompaniaByNombre("Unifin");

        result.forEach(g -> {
            if (g.getNombre().equals("Inversiones")){
                g.getCompanias().add(companiaPadre);
                grupoService.save(g);
            }
        });
    }


    public void editarGrupo(){
        Grupo grupo = grupoService.findGrupoByNombre("gpo multivatouch");
    }

    @Test
    public void agregaRelacionUsuarioGrupo(){

        Usuario user = usuarioService.findUsuarioBynombre("Administrator").stream().findFirst().get();

        Grupo grupo = grupoService.findGrupoByNombre("Nomina Legosoft");

        Grupo grupo2 = grupoService.findGrupoByNombre("gpo multivatouch");

        user.getGrupos().add(grupo);
        user.getGrupos().add(grupo2);

        usuarioService.saveOrUpdate(user);
    }


    @Test
    public void generaRelacioncompaniausuario(){

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        int contador = 0;

        Compania compania = companiaService.findCompaniaByNombre("Legolock");
        Compania compania2 = companiaService.findCompaniaByNombre("Lmass");
        for (Usuario u: usuarios) {
            contador++;

            if (contador <= 25){

                if (contador <= 10 ){
                    u.getCommpanias().add(compania);
                }else {
                    u.getCommpanias().add(compania2);
                }

                usuarioService.saveOrUpdate(u);
            }
        }

    }

    @Test
    public void relacionaUsuarioACompania(){
        Usuario usuario = usuarioService.findUsuarioBynombre("mariana lopez  ").stream().findFirst().get();
        Compania com = companiaService.findCompaniaByNombre("Multiva");

        usuario.getCommpanias().add(com);

        usuarioService.saveOrUpdate(usuario);
    }

}
