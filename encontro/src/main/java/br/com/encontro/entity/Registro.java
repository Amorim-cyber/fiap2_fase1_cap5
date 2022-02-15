package br.com.encontro.entity;

import java.util.Calendar;

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
	
	@JoinColumn(name = "id_servico")
	@ManyToOne
	private Servico tipoServico;
	
	@JoinColumn(name = "id_morador")
	@ManyToOne
	private Morador morador;
	
	@JoinColumn(name = "id_prestador")
	@ManyToOne
	private Prestador prestador;
	

	public Registro() {
	}

	public Registro(int id, Calendar dataInicio, Calendar dataFim, Servico tipoServico, Morador morador,
			Prestador prestador) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.tipoServico = tipoServico;
		this.morador = morador;
		this.prestador = prestador;
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

	public Servico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Servico tipoServico) {
		this.tipoServico = tipoServico;
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
	
	
	
	
}
