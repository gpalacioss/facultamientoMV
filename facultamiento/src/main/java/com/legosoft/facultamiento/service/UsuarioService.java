package com.legosoft.facultamiento.service;

import java.util.List;
import java.util.Optional;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;

public interface UsuarioService {
	
	public List<Usuario> getAllUsuarios();
	
	public List<Usuario> findUsuarioBynombre(String nombre);
	
	public List<Usuario> getInfoUsuario(String nombre);
	
	public Perfil getPerfil();

	public Optional<Usuario> findUsuarioById(Long id);

	public Usuario saveOrUpdate(Usuario usuario);

	public List<Usuario> findUsuariosByNumeroCuenta(String numeroCuenta);

	/**
	 * Servicio que genera el json de forma grafica de los los perfiles, roles y permisos simples de un usuario
	 * @param nombreUsuario
	 * @return
	 */
	public String getUsuarioAndPermisosGraph(String nombreUsuario);

	/**
	 * Servicio que genera el json de forma de graficos de las empresas que puede ver un usuario administrador
	 * @param nombreAdmin
	 * @return
	 */
	public String getEmpresasbyAdministradorGraph(String nombreAdmin);


	/**
	 * Servicio que genera el json de forma de graficos de las empresas que puede ver un usuario administrador incluyendo los empleados que trabajan en las empresas
	 * @param nombreAdmin
	 * @return
	 */
	public String getEmpresaAndEmpleadosByAdministrador(String nombreAdmin);


	/**
	 * Servicio que genera el json ya en forma de grafos que muestra la empresas y sus cuentas de un administrador
	 * @param nombreAdmin
	 * @return
	 */
	public String getCuentasEmpresasByAdministrador(String nombreAdmin);

	/**
	 * Servicio que devuelve las cuentas y los permisos cuenta monto de un usuario
	 * @param nombreUsuario
	 * @return
	 */
	public String getPermisosCuentaMontoByUsuario(String nombreUsuario);


	/**
	 * Servicio que devuelve las cuentas y los permisos cuenta monto de un usuario incluyendo permisos ismples si tienen
	 * @param usuario
	 * @return
	 */
	public String getPermisosCuentaMontoAndSimplesByUsuario(String usuario);

}
