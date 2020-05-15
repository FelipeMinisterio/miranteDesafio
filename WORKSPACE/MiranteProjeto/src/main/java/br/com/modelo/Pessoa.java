package br.com.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "pessoa", schema ="mirante_database")
public class Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pessoa;
	private String nome;
	private String documento;
	private String dat_nascimento;
	private String nome_mae;
	private String nome_pai;
	private String dat_cadastro;
	private Integer cod_operador;
	private String tipo_pessoa;
	
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Integer getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome_mae() {
		return nome_mae;
	}
	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}
	public String getNome_pai() {
		return nome_pai;
	}
	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}

	public String getDat_nascimento() {
		return dat_nascimento;
	}
	public void setDat_nascimento(String dat_nascimento) {
		this.dat_nascimento = dat_nascimento;
	}
	public String getDat_cadastro() {
		return dat_cadastro;
	}
	public void setDat_cadastro(String dat_cadastro) {
		this.dat_cadastro = dat_cadastro;
	}
	public Integer getCod_operador() {
		return cod_operador;
	}
	public void setCod_operador(Integer cod_operador) {
		this.cod_operador = cod_operador;
	}
	public String getTipo_pessoa() {
		return tipo_pessoa;
	}
	public void setTipo_pessoa(String tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	
	
	
}
