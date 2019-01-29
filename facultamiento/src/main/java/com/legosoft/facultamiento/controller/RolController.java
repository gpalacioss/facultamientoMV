package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.models.old.FacultadCuenta;
import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private FacultadSerivice facultadSerivice;

    @Autowired
    private PermisoService permisoService;



    @RequestMapping(value = "/getAllRolles", method = RequestMethod.GET)
    public List<Rol> getAllRolles(){

        return rolService.getAllRoles();
    }

    @GetMapping(value = "/getRolbyNombre/{nombreRol}")
    public Rol getRolByNombre(@PathVariable("nombreRol") String nombreRol){
        return rolService.findRolByNombre(nombreRol);
    }

    @RequestMapping(value = "getPerfilesGroupByFacultad", method = RequestMethod.GET)
    public Map<List<String>, Set<String>> getPerfilesGroupByFacultad() {

        List<Perfil> result = perfilService.findAllPerfiles();


        Map<Set<Facultad>, Set<String>> collectFacultad = result.stream().collect(Collectors
                .groupingBy(Perfil::getFacultades, Collectors.mapping(Perfil::getNombre, Collectors.toSet())));

        int contador = 1;
        for (Set<Facultad> f : collectFacultad.keySet()) {


            if (f.size() >= 3) {

                Rol rol = new Rol();

                rol.setFechaCreacion(new Date());
                rol.setIsActivo(Boolean.TRUE);
                rol.setNombreRol("Rol:" + contador++);

                rol = rolService.findRolByNombre("Rol:" + contador++);

                Rol finalRol = rol;
                System.out.println("rol numero :: " + finalRol.getNombreRol());
                f.forEach(ff -> {

                    Set<CuentaNM> cuentaBancarias = new HashSet<>();

                    Map<String, Set<FacultadCuenta>> setFacultadCuenta =  ff.getLstFacultadCuenta().stream().collect(Collectors.groupingBy(FacultadCuenta::getNumCuentaByCuenta, Collectors.mapping(FacultadCuenta::getFacultadCuenta, Collectors.toSet())));

                    for (FacultadCuenta cf : ff.getLstFacultadCuenta()) {

                        System.out.println("tamaño de relaciones inicial:: " + ff.getLstFacultadCuenta().size());
                        System.out.println("cuenta:: " + cf.getCuenta().getNumeroCuenta());

//                        PermisoCuenta permisoCuenta = new PermisoCuenta();

                        CuentaNM cuentaNM = cuentaService.findCuentaNMBynumeroCuenta(cf.getCuenta().getNumeroCuenta());

                        if (cuentaNM == null) {

                            cuentaNM = new CuentaNM();

                            cuentaNM.setNumeroCuenta(cf.getCuenta().getNumeroCuenta());
                            cuentaNM.setIdUsuarioUltimaModificacion(cf.getCuenta().getIdUsuarioUltimaModificacion());

                            cuentaNM = cuentaService.save(cuentaNM);

                        }

                        cuentaBancarias.add(cuentaNM);

                    }

                    Permiso nf = facultadSerivice.findByNombrePermiso(ff.getNombre());

                    if (nf == null){

                        nf = new Permiso();

                        nf.setActivo(ff.isActivo());
                        nf.setDescripcion(ff.getDescripcion());
                        nf.setFechaModificacion(ff.getFechaModificacion());
                        nf.setHoraFinal(ff.getHoraFinal());
                        nf.setHoraInicio(ff.getHoraInicio());
                        nf.setIdPermiso(Long.parseLong(ff.getIdFacultad()));
                        nf.setIsRestriccionHorario(ff.isRestriccionHorario());
                        nf.setNombre(ff.getNombre());
                        nf.setTipoPermiso(ff.getTipoFacultad());


                        nf = facultadSerivice.saveOrUpdateFAcultadNuevaMultiva(nf);

                    }

                    finalRol.getFacultades().add(nf);
                    System.out.println("El rol : " + finalRol.getNombreRol() + " Contiene las siguientes facultades:: ");
                    System.out.println("facultad :: " + nf.getNombre());

                    cuentaBancarias.forEach(c -> System.out.println("cuentas bancarias :: " + c.getNumeroCuenta()));

                    //creaRelacionPermisoCuenta(nf, cuentaBancarias, setFacultadCuenta);

                });

                rolService.save(rol);
            }

        }

        return null;
    }


//    private void creaRelacionPermisoCuenta(Permiso permiso, Set<CuentaNM> cuentas, Map<String, Set<FacultadCuenta>> relaciones){
//
//        System.out.println("para este permiso tenemos lo siguiente :: " + permiso.getNombre());
//        System.out.println("Tamaño de la lista de cuentas:: " + cuentas.size());
//        System.out.println("lista de relaciones:: " + relaciones);
//
//       relaciones.forEach((s, facultadCuentas) -> {
//
//           PermisoCuenta pc;
//
//           FacultadCuenta fc = facultadCuentas.stream().findFirst().get();
//
//           pc = permisoService.findPermisoCuentaByPermisoAndCuenta(permiso.getNombre());
//
//           if (pc == null){
//
//               pc = new PermisoCuenta();
//
////               pc.setPermiso(permiso);
////               pc.setCuenta(cuentaService.findCuentaNMBynumeroCuenta(s));
////               pc.setLimiteIndividual(fc.getLimiteIndividual() == null || fc.getLimiteIndividual().isEmpty() ? new BigDecimal(0) : new BigDecimal(fc.getLimiteIndividual().replaceAll(",", "")));
////               pc.setLimiteMancomunado(fc.getLimiteMancomunado() == null || fc.getLimiteMancomunado().isEmpty() ? new BigDecimal(0) : new BigDecimal(fc.getLimiteMancomunado().replaceAll(",", "")));
////               pc.setLimiteOperable(fc.getLimiteOperable() == null || fc.getLimiteOperable().isEmpty() ? new BigDecimal(0) : new BigDecimal(fc.getLimiteOperable().replaceAll(",", "")));
////
////               System.out.println(" el permiso ---> ( " + pc.getPermiso().getNombre() + " ) --[HAS_PERMISO_CUENTA]-> " + "{ limiteOperable:: " + pc.getLimiteOperable() + ", limiteMancomunado:: " + pc.getLimiteMancomunado() + ", limiteIndividual :: " + pc.getLimiteIndividual() + " } -->  con la Cuenta  ---> (" + pc.getCuenta().getNumeroCuenta() +")");
//
//               permisoService.save(pc);
//           }
//
//       });



//    }

}
