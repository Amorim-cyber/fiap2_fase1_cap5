package br.com.encontro.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="tb_registro_servico")
public class Registro {
	
	@Id
	@SequenceGenerator(name="registro",sequenceName="sq_tb_registro",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="registro")
	@Column(name="id_registro")
	private int id;

	@CreationTimestamp
	@Column(name="dt_data_cadastro")
	private Calendar dataCadastro;
	
	@UpdateTimestamp
	@Column(name="dt_data_modificacao")
	private Calendar dataModificacao;
	
	@JoinColumn(name = "id_servico")
	@ManyToOne
	private Servico tipoServico;
	
	@JoinColumn(name = "id_morador")
	@ManyToOne
	private Morador morador;
	
	@JoinColumn(name = "id_prestador")
	@ManyToOne
	private Morador prestador;

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	
	
}
