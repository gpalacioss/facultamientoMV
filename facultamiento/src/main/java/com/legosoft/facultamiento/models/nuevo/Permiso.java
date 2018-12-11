package com.legosoft.facultamiento.models.nuevo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Permiso")
public class Permiso {


    @Id
    @GeneratedValue
    private Long id;

    private Long idPermiso;

    private String nombre;

    private String descripcion;

    private String horaInicio;

    private String horaFinal;

    private String isRestriccionHorario;

    private String tipoPermiso;

    private String fechaModificacion;

    private Boolean isActivo;

    @JsonIgnoreProperties("permiso")
    @Relationship(type = "HAS_PERMISO_CUENTA", direction = Relationship.OUTGOING)
    private Set<PermisoCuenta> lstPermisoCuentas = new HashSet<>();

    @Relationship(type = "USUARIO_HAS_CUENTA_PERMISO")
    private Set<UsuarioPermisoCuenta> usuarioPermisoCuentas = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getIsRestriccionHorario() {
        return isRestriccionHorario;
    }

    public void setIsRestriccionHorario(String isRestriccionHorario) {
        this.isRestriccionHorario = isRestriccionHorario;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
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

    public Set<PermisoCuenta> getLstPermisoCuentas() {
        return lstPermisoCuentas;
    }

    public void setLstPermisoCuentas(Set<PermisoCuenta> lstPermisoCuentas) {
        this.lstPermisoCuentas = lstPermisoCuentas;
    }

    public Set<UsuarioPermisoCuenta> getUsuarioPermisoCuentas() {
        return usuarioPermisoCuentas;
    }

    public void setUsuarioPermisoCuentas(Set<UsuarioPermisoCuenta> usuarioPermisoCuentas) {
        this.usuarioPermisoCuentas = usuarioPermisoCuentas;
    }
}
