package br.com.encontro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_prestador")
public class Prestador {
	
	@Id
	@SequenceGenerator(name="prestador",sequenceName="sq_tb_prestador",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prestador")
	@Column(name="id_prestador")
	private int id;
	
	@Column(name="nm_prestador",nullable=false,length=100)
	private String nome;
	
	@Column(name="nr_morador",nullable=false)
	private Long telefone;
	
	public Prestador() {
	}

	public Prestador(String nome, Long telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	
	
	

}
