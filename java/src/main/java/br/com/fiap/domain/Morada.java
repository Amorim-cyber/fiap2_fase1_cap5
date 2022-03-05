package br.com.fiap.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_morada")
public class Morada {

	@Id
	@SequenceGenerator(name="morada",sequenceName="sq_tb_morada",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morada")
	@Column(name="id_morada")
	private int id;
	
	@Column(name="nr_morada",nullable=false)
	private int numero;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_morada")
	private Estrutura estrutura;
	
	@ManyToMany(mappedBy="moradas")
	private List<Morador> moradores;
	
	@JoinColumn(name = "id_condominio")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Condominio condominio;

	public Morada() {
	}

	public Morada(int id, int numero, Estrutura estrutura, List<Morador> moradores, Condominio condominio) {
		this.id = id;
		this.numero = numero;
		this.estrutura = estrutura;
		this.moradores = moradores;
		this.condominio = condominio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	
	
	
}
