package com.legosoft.facultamiento.service;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;

import java.util.List;

public interface CuentaService {


    /**
     * Servicio que cosnulta si una cuenta bancaria en el nuevo modelo por nombre
     * @param numeroCuenta
     * @return
     */
    public CuentaNM findCuentaNMBynumeroCuenta(String numeroCuenta);

    public CuentaNM save(CuentaNM cuentaNueva);

    /**
     * Consulta las cuentas por una lista  de numero de cuentas
     * @param listNumeroCuenta
     * @return
     */
    public List<CuentaNM> findCuentasByNumeroCuentaParameterList(String listNumeroCuenta);
}
