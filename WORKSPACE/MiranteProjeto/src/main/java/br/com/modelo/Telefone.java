package br.com.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="telefone", schema ="mirante_database")
public class Telefone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_telefone;
	private Integer ddd;
	private Integer numero;
	private String tipo;
	private String dat_cadastro;
	private Integer cod_operador;
	private Integer id_pessoa;
	public Integer getId_telefone() {
		return id_telefone;
	}
	public void setId_telefone(Integer id_telefone) {
		this.id_telefone = id_telefone;
	}
	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public Integer getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	
}
