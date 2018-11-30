package com.legosoft.facultamiento.models.nuevo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.math.BigDecimal;

@RelationshipEntity(type = "HAS_PERMISO_CUENTA")
public class PermisoCuenta {


    @Id
    @GeneratedValue
//    @Index(unique = true)
    private Long id;

    private BigDecimal limiteOperable;

    private BigDecimal limiteIndividual;

    private BigDecimal limiteMancomunado;


    @JsonIgnore
    @StartNode
//    @Index(unique = true)
    private Permiso permiso;

    @JsonIgnore
    @EndNode
//    @Index(unique = true)
    private CuentaNM cuenta;


    public BigDecimal getLimiteOperable() {
        return limiteOperable;
    }

    public void setLimiteOperable(BigDecimal limiteOperable) {
        this.limiteOperable = limiteOperable;
    }

    public BigDecimal getLimiteIndividual() {
        return limiteIndividual;
    }

    public void setLimiteIndividual(BigDecimal limiteIndividual) {
        this.limiteIndividual = limiteIndividual;
    }

    public BigDecimal getLimiteMancomunado() {
        return limiteMancomunado;
    }

    public void setLimiteMancomunado(BigDecimal limiteMancomunado) {
        this.limiteMancomunado = limiteMancomunado;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public CuentaNM getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaNM cuenta) {
        this.cuenta = cuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
