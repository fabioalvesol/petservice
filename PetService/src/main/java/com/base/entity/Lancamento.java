package com.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.base.discreto.TipoLancamento;
import com.base.validator.DecimalPositivo;

@Entity
@Table(name = "lancamento")
@Access(AccessType.FIELD)
@NamedQueries(@NamedQuery(name = Lancamento.findByPessoa, query = "Select obj from Lancamento obj where obj.pessoa = :pessoa"))
public class Lancamento extends AppEntity {
	private static final long serialVersionUID = -7520513263678154629L;

	public static final String findByPessoa = "Pessoa.findByPessoa";

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@NotEmpty
	@Size(max = 80)
	@Column(length = 80, nullable = false)
	private String descricao;

	@DecimalPositivo
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoLancamento tipo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = true)
	private Date dataPagamento;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
