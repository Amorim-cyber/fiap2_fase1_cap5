package br.com.fiap.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy="servicos")
	private List<Prestador> prestadores;
	
	public Servico() {
	}

	public Servico(int id, Ocupacao nome, List<Prestador> prestadores) {
		super();
		this.id = id;
		this.nome = nome;
		this.prestadores = prestadores;
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

	public List<Prestador> getPrestadores() {
		return prestadores;
	}

	public void setPrestadores(List<Prestador> prestadores) {
		this.prestadores = prestadores;
	}
	
	
	
}
