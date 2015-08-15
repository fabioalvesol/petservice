package com.base.financeiro.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.base.entity.Lancamento;

public class LancamentoDAO implements Serializable {
	private static final long serialVersionUID = 3909972750223191899L;

	private EntityManager manager;

	@Inject
	public LancamentoDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}

	public Lancamento porId(String id) {
		return manager.find(Lancamento.class, id);
	}

	public Lancamento guardar(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento " + "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}

}
