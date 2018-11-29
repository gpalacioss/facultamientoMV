package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;

public interface CuentaService {


    /**
     * Servicio que cosnulta si una cuenta bancaria en el nuevo modelo por nombre
     * @param numeroCuenta
     * @return
     */
    public CuentaNM findCuentaNMBynumeroCuenta(String numeroCuenta);

    public CuentaNM save(CuentaNM cuentaNueva);
}
