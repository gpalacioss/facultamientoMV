package com.legosoft.facultamiento;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.legosoft.facultamiento.models.nuevo.*;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

    @Autowired
    private CompaniaService companiaService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private PermisoService permisoService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private RolService rolService;

    @Test
    public void save(){

        Compania compania = companiaService.findCompaniaByNombre("Ixe");

        if (compania == null){

            compania =  new Compania();

            compania.setNombreCompania("Ixe");
            compania.setPadre(true);
            companiaService.save(compania);

        }else{
            System.out.println("La compañia ya existe");
        }


    }


    @Test
    public void creaRelacionCompaniaCompania(){

        Compania compania = companiaService.findCompaniaByNombre("Ixe");
        Compania companiaPadre = companiaService.findCompaniaByNombre("Banorte");

        compania.setPadre(false);

        companiaPadre.getCompaniaHijo().add(compania);

        companiaService.save(companiaPadre);

    }


    @Test
    public void getAllGrupos(){
        List<Grupo> result = grupoService.findAllGrupo();

        Compania companiaPadre = companiaService.findCompaniaByNombre("Legolock");

        result.forEach(g -> {
            if (g.getNombre().equals("Nomina Legosoft")){

//                companias permitidas
//                g.getCompanias().add(companiaPadre);

//                companias no permitidas
                g.getCompaniasNegadas().add(companiaPadre);

//                COMPANIAS PERMITIDAS SIN HERENCIA
//                g.getCompaniasPermitidasSinHerencia().add(companiaPadre);

                grupoService.save(g);

            }
        });
    }


    @Test
    public void editarGrupo(){
        Grupo grupo = grupoService.findGrupoByNombre("Transferencias");

        grupo.setNombre("Transferencias Bancarias");
        grupoService.save(grupo);
    }

    @Test
    public void editaUsuario(){
        Usuario usuario = usuarioService.findUsuarioBynombre("German Juarez  ").stream().findFirst().get();

        usuario.setNombre("Ricardo Legorreta");
        usuarioService.saveOrUpdate(usuario);
    }

    @Test
    public void agregaRelacionUsuarioGrupo(){

        Usuario user = usuarioService.findUsuarioBynombre("SANDRA VILLA CARRANZA ").stream().findFirst().get();

        Grupo grupo = grupoService.findGrupoByNombre("Transferencias Bancarias");

        Grupo grupo2 = grupoService.findGrupoByNombre("gpo multivatouch");
        Grupo grupo3 = grupoService.findGrupoByNombre("Inversiones");

        user.getGrupos().add(grupo);
        user.getGrupos().add(grupo2);
        user.getGrupos().add(grupo3);

        usuarioService.saveOrUpdate(user);
    }


    @Test
    public void generaRelacioncompaniausuario(){

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        Usuario usuaPorNombre = usuarioService.findUsuarioBynombre("Ricardo Legorreta").stream().findFirst().get();

        int contador = 0;

//        se comenta por que solo asigna un usuario en especifico a una compañia en especifica
//        Compania companiaPadre = companiaService.findCompaniaByNombre("Legosoft");
//        usuaPorNombre.getCommpanias().add(companiaPadre);
//        usuarioService.saveOrUpdate(usuaPorNombre);

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
        Usuario usuario = usuarioService.findUsuarioBynombre("Administrador Consulta AgreCapturista ").stream().findFirst().get();
        Compania com = companiaService.findCompaniaByNombre("Lmass");

        usuario.getCommpanias().add(com);

        usuarioService.saveOrUpdate(usuario);
    }

    @Test
    public void creaRelacionCompaniaCuenta(){

        Compania compania = companiaService.findCompaniaByNombre("Banorte");

        CuentaNM cuenta = cuentaService.findCuentaNMBynumeroCuenta("00000101958");
        CuentaNM cuenta2 = cuentaService.findCuentaNMBynumeroCuenta("00006690351");
        CuentaNM cuenta3 = cuentaService.findCuentaNMBynumeroCuenta("00000473464");
        CuentaNM cuenta4 = cuentaService.findCuentaNMBynumeroCuenta("00000101947");
        CuentaNM cuenta5 = cuentaService.findCuentaNMBynumeroCuenta("00000474045");
        CuentaNM cuenta6 = cuentaService.findCuentaNMBynumeroCuenta("00000137979");
        CuentaNM cuenta7= cuentaService.findCuentaNMBynumeroCuenta("00005754097");
        CuentaNM cuenta8 = cuentaService.findCuentaNMBynumeroCuenta("00000101998");
        CuentaNM cuenta9 = cuentaService.findCuentaNMBynumeroCuenta("00003612392");
        CuentaNM cuenta10= cuentaService.findCuentaNMBynumeroCuenta("00005754098");

        CuentaNM cuenta11 = cuentaService.findCuentaNMBynumeroCuenta("00000474061");
        CuentaNM cuenta12 = cuentaService.findCuentaNMBynumeroCuenta("00005750164");
        CuentaNM cuenta13= cuentaService.findCuentaNMBynumeroCuenta("00005816661");

//        compania.getCuentasEmpresas().add(cuenta);
        compania.getCuentasEmpresas().add(cuenta2);
//        compania.getCuentasEmpresas().add(cuenta3);
        compania.getCuentasEmpresas().add(cuenta4);
        compania.getCuentasEmpresas().add(cuenta5);
//        compania.getCuentasEmpresas().add(cuenta6);
//        compania.getCuentasEmpresas().add(cuenta7);
        compania.getCuentasEmpresas().add(cuenta8);
//        compania.getCuentasEmpresas().add(cuenta9);
//        compania.getCuentasEmpresas().add(cuenta10);
//        compania.getCuentasEmpresas().add(cuenta11);
        compania.getCuentasEmpresas().add(cuenta12);
//        compania.getCuentasEmpresas().add(cuenta13);


        companiaService.save(compania);


    }

    @Test
    public void negarOAgregarermisos(){


        Permiso permiso = permisoService.findPermisoByNombre("Mul_Nom_ConfigLayout");
        Permiso permiso2 = permisoService.findPermisoByNombre("Mul_OTP_Primer_Acceso");

        Permiso permiso3 = permisoService.findPermisoByNombre("Mul_Pago_Servicios");
        Permiso permiso4 = permisoService.findPermisoByNombre("Mul_Tran_Donaciones");
        Permiso permiso5 = permisoService.findPermisoByNombre("Mul_Tran_CargaMultiples");

        Usuario usuario = usuarioService.findUsuarioBynombre("Zaira Casimiro").stream().findFirst().get();

        usuario.getPermisosNegados().add(permiso);
        usuario.getPermisosNegados().add(permiso2);
        usuario.getPermisosNegados().add(permiso3);
        usuario.getPermisosNegados().add(permiso4);
        usuario.getPermisosNegados().add(permiso5);

//        usuario.getPermisoAgregados().add(permiso);
//        usuario.getPermisoAgregados().add(permiso2);

        usuarioService.saveOrUpdate(usuario);


    }

    @Test
    public void asignaPerfilAUsuario(){

        Optional<PerfilNM> perfil = perfilService.findPerfilNMByNombre("perfil-generado-13");

        perfil.get().setNombre("Director de Banca");

        PerfilNM p = perfilService.saveOrUpdate(perfil.get());

        Usuario usuaPorNombre = usuarioService.findUsuarioBynombre("Administrador Consulta AgreCapturista ").stream().findFirst().get();

        usuaPorNombre.getPerfiles().add(p);

        usuarioService.saveOrUpdate(usuaPorNombre);

    }

    @Test
    public void validaRol(){
        List<Rol> result = rolService.getAllRoles();

        System.out.println(result.size());

        result.forEach(r -> {
            if (r.getFacultades().size() == 0){
                rolService.deleteRol(r);
            }
        });
    }

    @Test
    public void validaPerfl(){
        List<PerfilNM> perfiles = perfilService.getAllperfilesNuevos();

        perfiles.forEach(p -> {
            if (p.getRoles().size() == 0){
                perfilService.deletePerfilNuevo(p);
            }
        });
    }

    @Test
    public void pruebaGrafos(){

        List<Grupo> result = grupoService.findAllGrupo();

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        ObjectNode graphNodes = new ObjectMapper().createObjectNode();


        result.forEach(g ->{

            if (g.getCompanias().size() > 0){

                Map<String, Object> grupo  =  new HashMap<>();
                grupo.put("caption", g.getNombre());
                grupo.put("type", "Grupo");
                grupo.put("id", g.getIdGrupo());

                ObjectMapper mapperGrupo = new ObjectMapper();
                JsonNode jsonNodeGrupo = mapperGrupo.convertValue(grupo, JsonNode.class);

                try {
                    arrayNode.add(jsonNodeGrupo);

                }catch (Exception e){

                }

                g.getCompanias().forEach(c->{

                    Map<String, Object> compania  =  new HashMap<>();
                    compania.put("caption", c.getNombreCompania());
                    compania.put("type", "Compania");
                    compania.put("id", c.getIdCompania());

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNodeMap = mapper.convertValue(compania, JsonNode.class);

                    try {
                        arrayNode.add(jsonNodeMap);

                    }catch (Exception e){

                    }

                    Map<String, Object> relacion = new HashMap<>();

                    relacion.put("source", g.getIdGrupo());
                    relacion.put("target", c.getIdCompania());
                    relacion.put("caption", "ALLOW");


                    ObjectMapper mapperRelacion = new ObjectMapper();
                    JsonNode jsonNodeRelacion = mapperRelacion.convertValue(relacion, JsonNode.class);

                    try {
                        arrayEdges.add(jsonNodeRelacion);

                    }catch (Exception e){

                    }

                    System.out.println(c.getNombreCompania());

                });

            }

        });

        graphNodes.set("nodes", arrayNode);
        graphNodes.set("edges", arrayEdges);

        System.out.println("valor del json:: " + graphNodes.toString());
    }


    @Test
    public void  service(){

        String result = usuarioService.getUsuarioAndPermisosGraph("Ricardo Legorreta");

        System.out.println(result);
    }

//    @Test
//    public void empresasByUsuario(){
//        String result = usuarioService.getEmpresasbyAdministradorGraph("Jorge Brant");
//        System.out.println(result);
//    }
//
//
//    @Test
//    public void empresasAndEmpleadosByUsuario(){
//        String result = usuarioService.getEmpresaAndEmpleadosByAdministrador("Jorge Brant");
//        System.out.println(result);
//    }
//
//    @Test
//    public void cuentasEmpresas(){
//        String result = usuarioService.getCuentasEmpresasByAdministrador("Jorge Brant");
//        System.out.println(result);
//    }
//
//    @Test
//    public void permisosCuentaMonto(){
//        String result = usuarioService.getPermisosCuentaMontoByUsuario("kik ros mat ");
//        System.out.println(result);
//    }
//
//    @Test
//    public void permisosCuentaMontoAndSimples(){
//        String result = usuarioService.getPermisosCuentaMontoAndSimplesByUsuario("Administrador Consulta AgreCapturista ");
//        System.out.println(result);
//    }

}
