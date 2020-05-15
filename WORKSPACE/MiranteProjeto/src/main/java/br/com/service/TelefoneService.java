package br.com.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.modelo.Telefone;

@Stateless
@Path("telefones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelefoneService {

	@PersistenceContext(unitName="OperadoresPU")
	private EntityManager entityManager;
	
	List<Telefone> telefones;
	
    @GET
    @Path("/pessoa/{id}")
    public List<Telefone> getTelefoneByIdPessoa(@PathParam("id") Integer id_pessoa) {
    	Query query = entityManager.createQuery("select t from Telefone t where t.id_pessoa = '"+id_pessoa+"'");
    	return query.getResultList();
    }
    @GET
    @Path("{id}")
    public Telefone getTelefoneById(@PathParam("id") Integer id_telefone) {
    	Query query = entityManager.createQuery("select t from Telefone t where t.id_telefone = '"+id_telefone+"'");
    	return (Telefone) query.getSingleResult();
    }
    
	@POST
	public Telefone adicionar(Telefone telefone) {	
		entityManager.persist(telefone);
		return telefone;
	}
	
	@DELETE
	@Path("{id}")
	public Telefone excluir(@PathParam("id") Integer id) {
		Telefone telefone = getTelefoneById(id);		
		entityManager.remove(telefone);
		return telefone;
	}
	
	
}
