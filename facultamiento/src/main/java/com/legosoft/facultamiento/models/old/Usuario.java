package com.legosoft.facultamiento.models.old;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.legosoft.facultamiento.models.nuevo.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String idUsuario;
	
	private String nombre;
	
	private String telefono;
	
	private String email;
	
	private Boolean isInterno;
	
	private Boolean isActivo;
	
//	@Relationship(type = "HAS_PERFIL", direction = Relationship.OUTGOING)
//	private Set<Perfil> perfiles = new HashSet<>();

//	@Relationship(type = "HAS_CUENTA", direction = Relationship.OUTGOING)
//	private Set<Cuenta> cuentasBancarias = new HashSet<>();

	@Relationship(type = "HAS_PERFIL_NM", direction = Relationship.OUTGOING)
	private Set<PerfilNM> perfiles = new HashSet<>();

	@Relationship(type = "USUARIO_HAS_CUENTA", direction = Relationship.OUTGOING)
	private Set<CuentaNM> cuentasBancariasUsuario = new HashSet<>();

	@Relationship(type = "USUARIO_HAS_CUENTA_PERMISO")
	private Set<PermisoCuenta> permisoCuentas = new HashSet<>();

	@Relationship(type = "MEMBER_OF")
	private Set<Grupo> grupos = new HashSet<>();

	@Relationship(type = "TRABAJA_EN")
	private Set<Compania> commpanias = new HashSet<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
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

	public Set<PermisoCuenta> getPermisoCuentas() {
		return permisoCuentas;
	}

	public void setPermisoCuentas(Set<PermisoCuenta> permisoCuentas) {
		this.permisoCuentas = permisoCuentas;
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
}
