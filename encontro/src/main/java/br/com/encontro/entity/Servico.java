package br.com.encontro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_servico")
public class Servico {

	@Id
	@SequenceGenerator(name="servico",sequenceName="sq_tb_servico",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servico")
	@Column(name="id_servico")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nm_servico")
	private Ocupacao nome;
	
	public Servico() {
	}

	public Servico(Ocupacao nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ocupacao getNome() {
		return nome;
	}

	public void setNome(Ocupacao nome) {
		this.nome = nome;
	}
	
	
	
}
