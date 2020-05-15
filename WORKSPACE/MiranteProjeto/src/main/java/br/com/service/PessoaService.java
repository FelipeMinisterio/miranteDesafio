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

import br.com.modelo.Pessoa;

@Stateless
@Path("pessoas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaService {

	@PersistenceContext(unitName = "OperadoresPU")
	private EntityManager entityManager;
	
	List<Pessoa> pessoas;
	
	@GET
	public List<Pessoa> getPessoas(){
		Query query = entityManager.createQuery("select pe from Pessoa pe");
		return query.getResultList();
	}
	
	@POST
	public Pessoa adicionar(Pessoa pessoa) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat();
		pessoa.setDat_cadastro(df.format(date));
		entityManager.persist(pessoa);
		return pessoa;
	}
	
	@PUT
	@Path("{id}")
	public Pessoa atualizar(@PathParam("id") Integer id, Pessoa pessoa) {
		Pessoa pessoaEncontrada = getPessoa(id);
		pessoaEncontrada.setNome(pessoa.getNome());
		pessoaEncontrada.setDocumento(pessoa.getDocumento());
		pessoaEncontrada.setDat_nascimento(pessoa.getDat_nascimento());
		pessoaEncontrada.setNome_mae(pessoa.getNome_mae());
		pessoaEncontrada.setNome_pai(pessoa.getNome_pai());
		pessoaEncontrada.setDat_cadastro(pessoa.getDat_cadastro());
		pessoaEncontrada.setCod_operador(pessoa.getCod_operador());
		pessoaEncontrada.setTipo_pessoa(pessoa.getTipo_pessoa());
		entityManager.merge(pessoaEncontrada);
		return pessoa;
	}

	@DELETE
	@Path("{id}")
	public Pessoa excluir(@PathParam("id") Integer id) {
		Pessoa pessoa = getPessoa(id);
		entityManager.remove(pessoa);
		return pessoa;
	}
	
	@GET
	@Path("{id}")
	public Pessoa getPessoa(@PathParam("id") Integer id) {
		return entityManager.find(Pessoa.class,id);
	}
	
}
