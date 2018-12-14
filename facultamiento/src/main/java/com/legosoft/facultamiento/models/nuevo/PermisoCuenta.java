package com.legosoft.facultamiento.models.nuevo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legosoft.facultamiento.models.old.Usuario;
import org.neo4j.ogm.annotation.*;

import java.math.BigDecimal;

@RelationshipEntity(type = "USUARIO_HAS_CUENTA_PERMISO")
public class PermisoCuenta {


    @Id
    @GeneratedValue
//    @Index(unique = true)
    private Long id;

    private BigDecimal limiteInferior;

    private BigDecimal limiteSuperior;

    @JsonIgnore
    @StartNode
//    @Index(unique = true)
    private Usuario usuario;

    @JsonIgnore
    @EndNode
//    @Index(unique = true)
    private UsuarioPermisoCuenta usuarioPermisoCuenta;

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(BigDecimal limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioPermisoCuenta getUsuarioPermisoCuenta() {
        return usuarioPermisoCuenta;
    }

    public void setUsuarioPermisoCuenta(UsuarioPermisoCuenta usuarioPermisoCuenta) {
        this.usuarioPermisoCuenta = usuarioPermisoCuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
