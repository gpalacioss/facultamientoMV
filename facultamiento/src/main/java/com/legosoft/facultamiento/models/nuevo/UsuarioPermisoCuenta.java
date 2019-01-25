package com.legosoft.facultamiento.models.nuevo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legosoft.facultamiento.models.old.Usuario;
import org.neo4j.ogm.annotation.*;


import java.io.Serializable;
import java.math.BigDecimal;

@NodeEntity("UsuarioPermisoCuenta")
public class UsuarioPermisoCuenta  implements Serializable {

    @Id
    @GeneratedValue()
    @Property("idUsuarioPermisoCuenta")
    private Long idUsuarioPermisoCuenta;

    private BigDecimal limiteInferior;

    private BigDecimal limiteSuperior;

    @JsonIgnore
    @Relationship(type = "HAS_ACCOUNT_PERMIT", direction = Relationship.INCOMING)
    private Usuario usuarios;

    @JsonIgnore
    @Relationship(type = "HAS_ACCOUNT_PERMIT", direction = Relationship.INCOMING)
    private CuentaNM cuenta;

    @Relationship(type = "USUARIO_HAS_CUENTA_PERMISO", direction = Relationship.INCOMING)
    private Permiso permiso;

    public Long getIdUsuarioPermisoCuenta() {
        return idUsuarioPermisoCuenta;
    }

    public void setIdUsuarioPermisoCuenta(Long idUsuarioPermisoCuenta) {
        this.idUsuarioPermisoCuenta = idUsuarioPermisoCuenta;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public CuentaNM getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaNM cuenta) {
        this.cuenta = cuenta;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

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
}
