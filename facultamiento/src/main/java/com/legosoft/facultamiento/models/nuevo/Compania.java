package com.legosoft.facultamiento.models.nuevo;

import com.legosoft.facultamiento.models.old.Usuario;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Compania")
public class Compania implements Serializable {

    @Id
    @GeneratedValue
    private Long idCompania;

    @Index(unique = true)
    private String nombreCompania;

    private Boolean isPadre;

    @Relationship(type = "CHILD_OF", direction = Relationship.INCOMING)
    private Set<Compania> companiaHijo = new HashSet<>();

    @Relationship(type = "COMPANIA_HAS_CUENTA")
    private Set<CuentaNM> cuentasEmpresas = new HashSet<>();

    @Relationship(type = "TRABAJA_EN", direction = Relationship.INCOMING)
    private Set<Usuario> usuarios = new HashSet<>();

    public Long getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Long idCompania) {
        this.idCompania = idCompania;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public Boolean getPadre() {
        return isPadre;
    }

    public void setPadre(Boolean padre) {
        isPadre = padre;
    }

    public Set<Compania> getCompaniaHijo() {
        return companiaHijo;
    }

    public void setCompaniaHijo(Set<Compania> companiaHijo) {
        this.companiaHijo = companiaHijo;
    }

    public Set<CuentaNM> getCuentasEmpresas() {
        return cuentasEmpresas;
    }

    public void setCuentasEmpresas(Set<CuentaNM> cuentasEmpresas) {
        this.cuentasEmpresas = cuentasEmpresas;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
