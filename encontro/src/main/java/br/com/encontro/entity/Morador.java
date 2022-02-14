package br.com.encontro.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_morador")
public class Morador {

	@Id
	@SequenceGenerator(name="morador",sequenceName="sq_tb_morador",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="morador")
	@Column(name="id_morador")
	private int id;
	
	@Column(name="nm_morador",nullable=false,length=100)
	private String nome;
	
	@Column(name="nr_morador",nullable=false)
	private Long numero;
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name="id_morador"), 
	inverseJoinColumns = @JoinColumn(name="id_condominio"), name = "tb_registro_condominio")
	private List<Condominio> condominios;
	
	@OneToMany(mappedBy = "morador")
	private List<Registro> registros;
	
	public Morador() {
	}
	
	public Morador(int id, String nome, Long numero, List<Condominio> condominios, List<Registro> registros) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.condominios = condominios;
		this.registros = registros;
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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public List<Condominio> getCondominios() {
		return condominios;
	}

	public void setCondominios(List<Condominio> condominios) {
		this.condominios = condominios;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	
	
}
