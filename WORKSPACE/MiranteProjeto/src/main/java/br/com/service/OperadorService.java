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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.modelo.Operador;

@Stateless
@Path("operadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OperadorService {
	
	@PersistenceContext(unitName="OperadoresPU")
	private EntityManager entityManager;
	
	
	
	List<Operador> operadores;

	public OperadorService() {
	}

    @GET
    public List<Operador> getOperadores() {
        Query query = entityManager.createQuery("select o from Operador o");
        return query.getResultList();
    }
    
	@POST
	public Operador adicionar(Operador operador) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat();		
		operador.setDat_cadastro(df.format(date));
		entityManager.persist(operador);
		return operador;
	}

	@PUT
	@Path("{id}")
	public Operador atualizar(@PathParam("id") Integer id, Operador operador) {
		DateFormat df = new SimpleDateFormat();	
		Operador operadorEncontrado = getOperador(id);
		operadorEncontrado.setNome(operador.getNome());
		operadorEncontrado.setLogin(operador.getLogin());
		operadorEncontrado.setSenha(operador.getSenha());
		operadorEncontrado.setId_perfil(operador.getId_perfil());
		operadorEncontrado.setDat_cadastro(df.format(new Date()));
		entityManager.merge(operadorEncontrado);
		return operador;
	}

	@DELETE
	@Path("{id}")
	public Operador excluir(@PathParam("id") Integer id) {
		Operador operador = getOperador(id);		
		entityManager.remove(operador);
		return operador;
	}

	@GET
	@Path("{id}")
	public Operador getOperador(@PathParam("id") Integer id) {
		return entityManager.find(Operador.class, id);
	}
}
