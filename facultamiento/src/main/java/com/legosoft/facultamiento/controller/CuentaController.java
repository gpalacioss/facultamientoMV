package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping(value = "/getCuentaByNumerocuenta/{numCuenta}")
    public CuentaNM getCuentaByNumCuenta(@PathVariable("numCuenta") String numCuenta){
        return cuentaService.findCuentaNMBynumeroCuenta(numCuenta);
    }
}
