package com.legosoft.facultamiento.models.old;

import org.neo4j.ogm.annotation.*;
import java.io.Serializable;

@NodeEntity(label = "Cuenta")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue
    @Property(name = "idUsuarioCuenta")
    private Long idCuenta;

    @Property(name = "cuenta")
    private String numeroCuenta;

    @Property(name = "UsuarioUltimaModificacion")
    private String idUsuarioUltimaModificacion;

//    @Relationship(type = "HAS_CUENTA_FACULTAD", direction = Relationship.INCOMING)
//    private Set<FacultadCuenta> lstFacultadCuenta = new HashSet<>();

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getIdUsuarioUltimaModificacion() {
        return idUsuarioUltimaModificacion;
    }

    public void setIdUsuarioUltimaModificacion(String idUsuarioUltimaModificacion) {
        this.idUsuarioUltimaModificacion = idUsuarioUltimaModificacion;
    }
}
