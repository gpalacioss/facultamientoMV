package com.legosoft.facultamiento.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.repository.PerfilRepository;
import com.legosoft.facultamiento.repository.UsuarioRepository;
import com.legosoft.facultamiento.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Usuario> getAllUsuarios(){
		List<Usuario> result = repository.findAll();
		return result;
	}

	public List<Usuario> getInfoUsuario(String nombre){
		List<Usuario> result = repository.getInfoUsuario(nombre);
		return result;
	}
	
	public Perfil getPerfil() {
		Perfil result =  perfilRepository.buscaPerfilAndUsario();
		return result;
	}
	
	public List<Usuario> findUsuarioBynombre(String nombre) {
		List<Usuario> result = repository.findByNombre(nombre);
		return result;
	}

	public Usuario findUsuarioByNombreUsuario(String username){
	    return repository.findByNombreUsuario(username);
    }
	
	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public void deleteUsuario(Usuario usuario){
	    repository.delete(usuario);
    }

	public Optional<Usuario> findUsuarioById(Long id){
		return repository.findById(id);
	}

	public Usuario saveOrUpdate(Usuario usuario){
		return repository.save(usuario);
	}

	public List<Usuario> findUsuariosByNumeroCuenta(String numeroCuenta){
		return repository.findusuariosByNumeroCuenta(numeroCuenta);
	}


	public String getUsuarioAndPermisosGraph(String nombreUsuario){

		ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
		ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

		Usuario repoUsuario = repository.getUsuarioAndPermisos(nombreUsuario);

		arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getIdUsuario(), 1));

		repoUsuario.getPermisoAgregados().forEach(pa -> {

			arrayNode.add(generaNodo(pa.getNombre(), "permiso", pa.getIdPermiso(), 2));
			arrayEdges.add(generaRelacion(repoUsuario.getIdUsuario(), pa.getIdPermiso(), "ADDED_PERMIT"));

		});

		repoUsuario.getPerfiles().forEach(p -> {

			arrayNode.add(generaNodo(p.getNombre(), "perfil", p.getIdPerfil(), 3));
			arrayEdges.add(generaRelacion(repoUsuario.getIdUsuario(), p.getIdPerfil(), "HAS_PROFILE"));

			p.getRoles().forEach(r -> {

				arrayNode.add(generaNodo(r.getNombreRol(), "rol", r.getIdRol(), 4));
				arrayEdges.add(generaRelacion(p.getIdPerfil(), r.getIdRol(), "HAS_ROLE"));

				r.getFacultades().forEach(pr ->{

					arrayNode.add(generaNodo(pr.getNombre(), "permiso", pr.getIdPermiso(), 2));
					arrayEdges.add(generaRelacion(r.getIdRol(), pr.getIdPermiso(), "HAS_PERMIT"));

				});
			});

		});


		System.out.println(generaGraph(arrayNode, arrayEdges).toString());

		return  generaGraph(arrayNode, arrayEdges).toString();

	}


	public String getEmpresasbyAdministradorGraph(String nombreAdmin){

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        Usuario repoUsuario = repository.empresasbyAdministradorGraph(nombreAdmin);

        arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getId(), 1));

        repoUsuario.getGrupos().forEach(g -> {
            arrayNode.add(generaNodo(g.getNombre(), "grupo", g.getIdGrupo(), 2));
            arrayEdges.add(generaRelacion(repoUsuario.getId(), g.getIdGrupo(), "MEMBER_OF"));

            g.getCompanias().forEach(c -> {
                arrayNode.add(generaNodo(c.getNombreCompania(), "compania", c.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), c.getIdCompania(), "ALLOW"));

                c.getCompaniaHijo().forEach(ch -> {
                    arrayNode.add(generaNodo(ch.getNombreCompania(), "compania", ch.getIdCompania(), 3));
                    arrayEdges.add(generaRelacion(ch.getIdCompania(), c.getIdCompania(), "CHILD_OF"));
                });
            });

            g.getCompaniasPermitidasSinHerencia().forEach(csh -> {
                arrayNode.add(generaNodo(csh.getNombreCompania(), "compania", csh.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), csh.getIdCompania(), "ALLOWED_DO_NOT_INHERIT"));
            });
        });

        return generaGraph(arrayNode, arrayEdges).toString();

    }

    public String getEmpresaAndEmpleadosByAdministrador(String nombreAdmin){

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        List<Usuario> lstrepoUsuario =repository.empleadosAndEmpresasByAdministradorGraph(nombreAdmin);
        Usuario repoUsuario = lstrepoUsuario.stream().findFirst().get();

        arrayNode.add(generaNodo(repoUsuario.getNombre(),"administrador", repoUsuario.getId(), 1));

        repoUsuario.getGrupos().forEach(g -> {
            arrayNode.add(generaNodo(g.getNombre(), "grupo", g.getIdGrupo(), 2));
            arrayEdges.add(generaRelacion(repoUsuario.getId(), g.getIdGrupo(), "MEMBER_OF"));

            g.getCompanias().forEach(c -> {
                arrayNode.add(generaNodo(c.getNombreCompania(), "compania", c.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), c.getIdCompania(), "ALLOW"));

                c.getUsuarios().forEach(em -> {
                    arrayNode.add(generaNodo(em.getNombre(), "usuario", em.getId(), 4));
                    arrayEdges.add(generaRelacion(em.getId(), c.getIdCompania(), "WORKS_FOR"));
                });

                c.getCompaniaHijo().forEach(ch -> {
                    arrayNode.add(generaNodo(ch.getNombreCompania(), "compania", ch.getIdCompania(), 3));
                    arrayEdges.add(generaRelacion(ch.getIdCompania(), c.getIdCompania(),"CHILD_OF"));

                    ch.getUsuarios().forEach(emeh -> {
                        arrayNode.add(generaNodo(emeh.getNombre(), "usuario", emeh.getId(), 4));
                        arrayEdges.add(generaRelacion( emeh.getId(), ch.getIdCompania(), "WORKS_FOR"));
                    });

                });
            });

            g.getCompaniasPermitidasSinHerencia().forEach(csh -> {
                arrayNode.add(generaNodo(csh.getNombreCompania(), "compania", csh.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), csh.getIdCompania(), "ALLOWED_DO_NOT_INHERIT"));

                csh.getUsuarios().forEach(emesh -> {
                    arrayNode.add(generaNodo(emesh.getNombre(), "usuario", emesh.getId(), 4));
                    arrayEdges.add(generaRelacion(emesh.getId(), csh.getIdCompania(), "WORKS_FOR"));
                });

            });
        });

        return generaGraph(arrayNode, arrayEdges).toString();
    }

    public String getCuentasEmpresasByAdministrador(String nombreAdmin){

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        Usuario repoUsuario = repository.cuentasEmpresasByAdministrador(nombreAdmin);

        arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getId(), 1));

        repoUsuario.getGrupos().forEach(g -> {
            arrayNode.add(generaNodo(g.getNombre(), "grupo", g.getIdGrupo(), 2));
            arrayEdges.add(generaRelacion(repoUsuario.getId(), g.getIdGrupo(), "MEMBER_OF"));

            g.getCompanias().forEach(c -> {
                arrayNode.add(generaNodo(c.getNombreCompania(), "compania", c.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), c.getIdCompania(), "ALLOW"));

                c.getCuentasEmpresas().forEach(ce -> {
                    arrayNode.add(generaNodo(ce.getNumeroCuenta(), "cuenta", ce.getIdCuenta(), 4));
                    arrayEdges.add(generaRelacion(c.getIdCompania(), ce.getIdCuenta(), "HAS_ACCOUNT"));
                });

                c.getCompaniaHijo().forEach(ch -> {
                    arrayNode.add(generaNodo(ch.getNombreCompania(), "compania", ch.getIdCompania(), 3));
                    arrayEdges.add(generaRelacion(c.getIdCompania(), ch.getIdCompania(), "CHILD_OF"));

                    ch.getCuentasEmpresas().forEach(cech -> {
                        arrayNode.add(generaNodo(cech.getNumeroCuenta(), "cuenta", cech.getIdCuenta(), 4));
                        arrayEdges.add(generaRelacion(ch.getIdCompania(), cech.getIdCuenta(), "HAS_ACCOUNT"));
                    });


                });
            });

            g.getCompaniasPermitidasSinHerencia().forEach(csh -> {
                arrayNode.add(generaNodo(csh.getNombreCompania(), "compania", csh.getIdCompania(), 3));
                arrayEdges.add(generaRelacion(g.getIdGrupo(), csh.getIdCompania(), "ALLOWED_DO_NOT_INHERIT"));

                csh.getCuentasEmpresas().forEach(cecsh -> {
                    arrayNode.add(generaNodo(cecsh.getNumeroCuenta(), "cuenta", cecsh.getIdCuenta(), 4));
                    arrayEdges.add(generaRelacion(csh.getIdCompania(), cecsh.getIdCuenta(), "HAS_ACCOUNT"));
                });

            });
        });

        return generaGraph(arrayNode, arrayEdges).toString();

    }


    public String getPermisosCuentaMontoByUsuario(String nombreUsuario){

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        Usuario repoUsuario = repository.permisosCuentaMontoByUsuario(nombreUsuario);

        arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getIdUsuario(), 1));

        repoUsuario.getCuentasBancariasUsuario().forEach(c -> {
            arrayNode.add(generaNodo(c.getNumeroCuenta(), "cuenta", c.getIdCuenta(), 2));
            arrayEdges.add(generaRelacion(repoUsuario.getIdUsuario(), c.getIdCuenta(), "HAS_ACCOUNT"));
        });

        repoUsuario.getUsuarioPermisoCuentas().forEach(pc -> {
            arrayNode.add(generaNodo(pc.getPermiso().getNombre(), "permisoCuentaMonto", pc.getIdUsuarioPermisoCuenta(), 2));
//            arrayEdges.add(generaRelacion(repoUsuario.getIdUsuario(), pc.getIdUsuarioPermisoCuenta(), "USUARIO_HAS_CUENTA_PERMISO"));


//            arrayNode.add(generaNodo(pc.getPermiso().getNombre(), "permiso", pc.getPermiso().getIdPermiso(), 3));
//            arrayEdges.add(generaRelacion(pc.getPermiso().getIdPermiso(), pc.getIdUsuarioPermisoCuenta(), "USUARIO_HAS_CUENTA_PERMISO"));


            arrayNode.add(generaNodo(pc.getCuenta().getNumeroCuenta(), "cuenta", pc.getCuenta().getIdCuenta(), 4));
            arrayEdges.add(generaRelacion(pc.getCuenta().getIdCuenta(), pc.getIdUsuarioPermisoCuenta(), "HAS_ACCOUNT_PERMIT"));

        });
        System.out.println(generaGraph(arrayNode, arrayEdges).toString());
	    return generaGraph(arrayNode, arrayEdges).toString();
    }

    public String getPermisosCuentaMontoAndSimplesByUsuario(String usuario){

        ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
        ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

        Usuario repoUsuario = repository.permisoCuentaMontoAndSimplebyUsuario(usuario);

        arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getId(), 1));

        repoUsuario.getPerfiles().forEach(p -> {

            arrayNode.add(generaNodo(p.getNombre(), "perfil", p.getIdPerfil(),2));
            arrayEdges.add(generaRelacion(repoUsuario.getId(), p.getIdPerfil(), "HAS_PROFILE"));

            p.getRoles().forEach(r -> {

                arrayNode.add(generaNodo(r.getNombreRol(), "rol", r.getIdRol(), 3));
                arrayEdges.add(generaRelacion(p.getIdPerfil(), r.getIdRol(), "HAS_ROLE"));

                r.getFacultades().forEach(pr ->{
                    arrayNode.add(generaNodo(pr.getNombre(), "permiso", pr.getIdPermiso(), 4));
                    arrayEdges.add(generaRelacion(r.getIdRol(), pr.getIdPermiso(), "HAS_PERMIT"));
                });
            });
        });

        repoUsuario.getPermisoAgregados().forEach(pa -> {

            arrayNode.add(generaNodo(pa.getNombre(), "permiso", pa.getIdPermiso(), 4));
            arrayEdges.add(generaRelacion(repoUsuario.getId(), pa.getIdPermiso(), "ADDED_PERMIT"));

        });


        repoUsuario.getUsuarioPermisoCuentas().forEach(pc -> {
            arrayNode.add(generaNodo(pc.getPermiso().getNombre(), "permisoCuentaMonto", pc.getIdUsuarioPermisoCuenta(), 5));
            arrayEdges.add(generaRelacion(repoUsuario.getIdUsuario(), pc.getIdUsuarioPermisoCuenta(), "HAS_ACCOUNT_PERMIT"));


//            arrayNode.add(generaNodo(pc.getPermiso().getNombre(), "permiso", pc.getPermiso().getIdPermiso(), 4));
//            arrayEdges.add(generaRelacion(pc.getPermiso().getIdPermiso(), pc.getIdUsuarioPermisoCuenta(), "USUARIO_HAS_CUENTA_PERMISO"));


            arrayNode.add(generaNodo(pc.getCuenta().getNumeroCuenta(), "cuenta", pc.getCuenta().getIdCuenta(),6));
            arrayEdges.add(generaRelacion(pc.getCuenta().getIdCuenta(), pc.getIdUsuarioPermisoCuenta(), "HAS_ACCOUNT_PERMIT"));

        });

        return  generaGraph(arrayNode, arrayEdges).toString();
    }

	private ObjectNode generaGraph(ArrayNode arrayNodes, ArrayNode arrayRelationship){

		ObjectNode graphNodes = new ObjectMapper().createObjectNode();

		graphNodes.set("nodes", arrayNodes);
		graphNodes.set("edges", arrayRelationship);

		return  graphNodes;
	}

	private JsonNode generaNodo(String caption, String type, Long id, int cluster){

		Map<String, Object> nodo  =  new HashMap<>();

        nodo.put("id", id);
		nodo.put("name", caption);
        nodo.put("cluster", cluster);
        nodo.put("nodeType", type);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNodeMap = mapper.convertValue(nodo, JsonNode.class);

		return jsonNodeMap;

	}

	private JsonNode generaRelacion(Long idStartNode, Long idEndNode, String etiquetaRelacion){

		Map<String, Object> relacion = new HashMap<>();

		relacion.put("source", idStartNode);
		relacion.put("target", idEndNode);
		relacion.put("edgeType", etiquetaRelacion);

		ObjectMapper mapperRelacion = new ObjectMapper();
		JsonNode jsonNodeRelacion = mapperRelacion.convertValue(relacion, JsonNode.class);

		return  jsonNodeRelacion;
	}

    private void  generaArchivoTxt(String json, String nombreArchivo){
//        try {
//
//            //windows
//            String ruta = "C:\\Users\\Gusstavo\\Documents\\0.4.2\\data/" + nombreArchivo + ".json";
//
////            String ruta = "/home/usuario/Imágenes/precatica graficas/0.4.2/data/" + nombreArchivo + ".json";
//
//            File file = new File(ruta);
//            // Si el archivo no existe es creado
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(json);
//            bw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public void deletePermisosAgregados(String nombreUsuario, String nombrePermiso){
	    repository.deletePermisosAgregados(nombreUsuario, nombrePermiso);
    }
}
