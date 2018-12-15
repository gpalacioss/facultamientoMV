package com.legosoft.facultamiento;

import com.legosoft.facultamiento.models.nuevo.Compania;
import com.legosoft.facultamiento.models.nuevo.Grupo;
import com.legosoft.facultamiento.service.CompaniaService;
import com.legosoft.facultamiento.service.GrupoService;
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

        Compania companiaPadre = companiaService.findCompaniaByNombre("Multiva");

        result.forEach(g -> {
            if (g.getNombre().equals("gpo multivatouch")){
                g.getCompanias().add(companiaPadre);
                grupoService.save(g);
            }
        });
    }


    public void editarGrupo(){
        Grupo grupo = grupoService.findGrupoByNombre("gpo multivatouch");
    }

}
