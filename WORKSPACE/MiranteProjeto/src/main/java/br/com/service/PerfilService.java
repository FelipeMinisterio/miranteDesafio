package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.modelo.Perfil;

@Stateless
@Path("perfis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PerfilService {
	@PersistenceContext(unitName = "OperadoresPU")
	private EntityManager entityManager;
	
	List<Perfil> perfis;
	
	@GET
	public List<Perfil> getPerfis(){
		Query query = entityManager.createQuery("select p from Perfil p where p.id_perfil != 1");
		return query.getResultList();
	}
}
