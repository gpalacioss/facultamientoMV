package com.legosoft.facultamiento.service.impl;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import com.legosoft.facultamiento.repository.CuentaNMRepository;
import com.legosoft.facultamiento.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cuentaService")
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaNMRepository cuentaNMRepository;

    public CuentaNM findCuentaNMBynumeroCuenta(String numeroCuenta){
        CuentaNM cuenta =  cuentaNMRepository.findByNumeroCuenta(numeroCuenta);
        return  cuenta;
    }

    public CuentaNM save(CuentaNM cuentaNueva){
       return cuentaNMRepository.save(cuentaNueva);
    }
}
