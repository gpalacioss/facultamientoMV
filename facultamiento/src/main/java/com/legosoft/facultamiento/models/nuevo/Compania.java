package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;
import java.util.Set;

@NodeEntity(label = "Compania")
public class Compania implements Serializable {

    @Id
    @GeneratedValue
    private Long idCompania;

    private String nombreCompania;

    private Boolean isPadre;

    private Set<Compania> companiaHijo;

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
}
