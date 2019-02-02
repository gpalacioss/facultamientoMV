package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.models.old.Cuenta;
import com.legosoft.facultamiento.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping(value = "saveCuenta")
    public CuentaNM saveCuenta(@RequestBody CuentaNM cuentaNM){
        return cuentaService.save(cuentaNM);
    }

    @GetMapping(value = "/getCuentaByNumerocuenta/{numCuenta}")
    public CuentaNM getCuentaByNumCuenta(@PathVariable("numCuenta") String numCuenta){
        return cuentaService.findCuentaNMBynumeroCuenta(numCuenta);
    }
}
