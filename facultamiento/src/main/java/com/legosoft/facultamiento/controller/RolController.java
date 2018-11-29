package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.nuevo.Permiso;
import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.models.old.FacultadCuenta;
import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.service.CuentaService;
import com.legosoft.facultamiento.service.FacultadSerivice;
import com.legosoft.facultamiento.service.PerfilService;
import com.legosoft.facultamiento.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



    @RequestMapping(value = "/getAllRolles", method = RequestMethod.GET)
    public List<Rol> getAllRolles(){

        return rolService.getAllRoles();
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

//                    Set<CuentaNM> cuentasNuevas = new HashSet<>();
                    Set<PermisoCuenta> lstPermisoCuentas = new HashSet<>();

                    PermisoCuenta permisoCuenta = new PermisoCuenta();

                    for (FacultadCuenta cf : ff.getLstFacultadCuenta()) {


                        //ff.getLstFacultadCuenta().forEach(cf -> {

                        CuentaNM cuentaNM = cuentaService.findCuentaNMBynumeroCuenta(cf.getCuenta().getNumeroCuenta());
                        //permisoCuenta = new PermisoCuenta();

                        if (cuentaNM == null) {

                            cuentaNM = new CuentaNM();

                            cuentaNM.setNumeroCuenta(cf.getCuenta().getNumeroCuenta());
                            cuentaNM.setIdUsuarioUltimaModificacion(cf.getCuenta().getIdUsuarioUltimaModificacion());

                            cuentaNM = cuentaService.save(cuentaNM);

                        }


                        permisoCuenta.setCuenta(cuentaNM);
                        permisoCuenta.setLimiteIndividual(cf.getLimiteIndividual() == null || cf.getLimiteIndividual().isEmpty() ? new BigDecimal(0) : new BigDecimal(cf.getLimiteIndividual().replaceAll(",", "")));
                        permisoCuenta.setLimiteMancomunado(cf.getLimiteMancomunado() == null || cf.getLimiteMancomunado().isEmpty() ? new BigDecimal(0) : new BigDecimal(cf.getLimiteMancomunado().replaceAll(",", "")));
                        permisoCuenta.setLimiteOperable(cf.getLimiteOperable() == null || cf.getLimiteOperable().isEmpty() ? new BigDecimal(0) : new BigDecimal(cf.getLimiteOperable().replaceAll(",", "")));
//                        cuentasNuevas.add(cuentaNM);
                        lstPermisoCuentas.add(permisoCuenta);

//                    });
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

                        permisoCuenta.setPermiso(nf);

                        //nf.setCuentasBancarias(cuentasNuevas);
                        nf.setLstPermisoCuentas(lstPermisoCuentas);

                        nf = facultadSerivice.saveOrUpdateFAcultadNuevaMultiva(nf);

                    }

                    rol.getFacultades().add(nf);
                    System.out.println("El rol : " + rol.getNombreRol() + " Contiene las siguientes facultades:: ");
                    System.out.println("facultad :: " + nf.getNombre());
                    nf.getLstPermisoCuentas().forEach(c -> System.out.println("numero de cuenta:: " + c.getCuenta().getNumeroCuenta()));

                });

                rolService.save(rol);
            }

        }

        return null;
    }

}
