package br.com.fiap.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@Column(name="nr_prestador",nullable=false)
	private int telefone;
	
	@OneToMany(mappedBy = "prestador")
	private List<Registro> registros;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(joinColumns = @JoinColumn(name="id_prestador"), 
	inverseJoinColumns = @JoinColumn(name="id_servico"), name = "tb_ocupacao")
	private List<Servico> servicos;
	
	public Prestador() {
	}
	
	public Prestador(int id, String nome, int telefone, List<Servico> servicos) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.servicos = servicos;
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

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	

}
