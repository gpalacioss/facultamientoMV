package com.legosoft.facultamiento.models.old;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legosoft.facultamiento.models.nuevo.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "User")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long idUsuario;
	
	private String nombre;

	private String nombreUsuario;
	
	private String telefono;
	
	private String email;
	
	private Boolean isInterno;
	
	private Boolean isActivo;

	private Boolean isAdministrador;
	
//	@Relationship(type = "HAS_PERFIL", direction = Relationship.OUTGOING)
//	private Set<Perfil> perfiles = new HashSet<>();

//	@Relationship(type = "HAS_CUENTA", direction = Relationship.OUTGOING)
//	private Set<Cuenta> cuentasBancarias = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "HAS_PROFILE")
	private Set<PerfilNM> perfiles = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "HAS_ACCOUNT")
	private Set<CuentaNM> cuentasBancariasUsuario = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "HAS_ACCOUNT_PERMIT")
	private Set<UsuarioPermisoCuenta> usuarioPermisoCuentas = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "MEMBER_OF")
	private Set<Grupo> grupos = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "WORKS_FOR")
	private Set<Compania> commpanias = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "DENIED")
	private Set<Permiso> permisosNegados = new HashSet<>();

	@JsonIgnore
	@Relationship(type = "ADDED_PERMIT")
	private Set<Permiso> permisoAgregados = new HashSet<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsInterno() {
		return isInterno;
	}

	public void setIsInterno(Boolean isInterno) {
		this.isInterno = isInterno;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Set<PerfilNM> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<PerfilNM> perfiles) {
		this.perfiles = perfiles;
	}

	public Set<CuentaNM> getCuentasBancariasUsuario() {
		return cuentasBancariasUsuario;
	}

	public void setCuentasBancariasUsuario(Set<CuentaNM> cuentasBancariasUsuario) {
		this.cuentasBancariasUsuario = cuentasBancariasUsuario;
	}

	public Set<UsuarioPermisoCuenta> getUsuarioPermisoCuentas() {
		return usuarioPermisoCuentas;
	}

	public void setUsuarioPermisoCuentas(Set<UsuarioPermisoCuenta> usuarioPermisoCuentas) {
		this.usuarioPermisoCuentas = usuarioPermisoCuentas;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Set<Compania> getCommpanias() {
		return commpanias;
	}

	public void setCommpanias(Set<Compania> commpanias) {
		this.commpanias = commpanias;
	}

	public Set<Permiso> getPermisosNegados() {
		return permisosNegados;
	}

	public void setPermisosNegados(Set<Permiso> permisosNegados) {
		this.permisosNegados = permisosNegados;
	}

	public Set<Permiso> getPermisoAgregados() {
		return permisoAgregados;
	}

	public void setPermisoAgregados(Set<Permiso> permisoAgregados) {
		this.permisoAgregados = permisoAgregados;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Boolean getAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(Boolean administrador) {
		isAdministrador = administrador;
	}


}
