package com.legosoft.facultamiento.models.nuevo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legosoft.facultamiento.models.old.Usuario;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


import java.io.Serializable;

@NodeEntity("UsuarioPermisoCuenta")
public class UsuarioPermisoCuenta  implements Serializable {

    @Id
    @GeneratedValue()
    private Long id;

    @JsonIgnore
    @Relationship(type = "USUARIO_HAS_CUENTA_PERMISO", direction = Relationship.INCOMING)
    private Usuario usuario;

    @JsonIgnore
    @Relationship(type = "USUARIO_HAS_CUENTA_PERMISO", direction = Relationship.INCOMING)
    private CuentaNM cuenta;

    @JsonIgnore
    @Relationship(type = "USUARIO_HAS_CUENTA_PERMISO", direction = Relationship.INCOMING)
    private Permiso permiso;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
