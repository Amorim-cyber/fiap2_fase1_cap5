package br.com.fiap.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Estado tipo;
	
	
	@CreationTimestamp
	@Column(name="dt_data_inicio")
	private Calendar dataInicio;
	
	@UpdateTimestamp
	@Column(name="dt_data_fim")
	private Calendar dataFim;
	
	@JoinColumn(name = "id_morador")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Morador morador;
	
	@JoinColumn(name = "id_prestador")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Prestador prestador;
	

	public Registro() {
	}

	public Registro(int id, Morador morador,
			Prestador prestador,Estado tipo) {
		this.id = id;
		this.morador = morador;
		this.prestador = prestador;
		this.tipo = tipo;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataModificacao(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Estado getTipo() {
		return tipo;
	}

	public void setTipo(Estado tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
