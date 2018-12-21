package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Grupo")
public class Grupo implements Serializable {


    @Id
    @GeneratedValue
    private Long idGrupo;


    private String nombre;

    private String fechaModificacion;

    private Boolean isActivo;


    @Relationship(type = "ALLOW")
    private Set<Compania> companias =  new HashSet<>();

    @Relationship(type = "DENIED")
    private Set<Compania> companiasNegadas = new HashSet<>();

    @Relationship(type = "ALLOWED_DO_NOT_INHERIT")
    private Set<Compania> companiasPermitidasSinHerencia = new HashSet<>();

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Boolean getActivo() {
        return isActivo;
    }

    public void setActivo(Boolean activo) {
        isActivo = activo;
    }

    public Set<Compania> getCompanias() {
        return companias;
    }

    public void setCompanias(Set<Compania> companias) {
        this.companias = companias;
    }

    public Set<Compania> getCompaniasNegadas() {
        return companiasNegadas;
    }

    public void setCompaniasNegadas(Set<Compania> companiasNegadas) {
        this.companiasNegadas = companiasNegadas;
    }


    public Set<Compania> getCompaniasPermitidasSinHerencia() {
        return companiasPermitidasSinHerencia;
    }

    public void setCompaniasPermitidasSinHerencia(Set<Compania> companiasPermitidasSinHerencia) {
        this.companiasPermitidasSinHerencia = companiasPermitidasSinHerencia;
    }
}
