package br.com.service;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.modelo.Operador;

@Stateless
@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {

	
	@PersistenceContext(unitName="OperadoresPU")
	private EntityManager entityManager;
	
	Operador operador;	
	
    @GET
    @Path("{login}")
    public Operador getLogin(@PathParam("login") String login) {
    	Query query = entityManager.createQuery("select o from Operador o where o.login = '"+login+"'");
    	operador = (Operador) query.getSingleResult();
    	return operador;
    }
	
	
}
