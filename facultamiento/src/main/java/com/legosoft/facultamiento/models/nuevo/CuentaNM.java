package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "CuentaNM")
public class CuentaNM implements Serializable {

    @Id
    @GeneratedValue
    private Long idCuenta;

    private String numeroCuenta;

    private String idUsuarioUltimaModificacion;

    @Relationship(type = "HAS_PERMISO_CUENTA", direction = Relationship.INCOMING)
    private Set<PermisoCuenta> lstPermisoCuentas = new HashSet<>();

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

    public Set<PermisoCuenta> getLstPermisoCuentas() {
        return lstPermisoCuentas;
    }

    public void setLstPermisoCuentas(Set<PermisoCuenta> lstPermisoCuentas) {
        this.lstPermisoCuentas = lstPermisoCuentas;
    }
}
