package com.base.financeiro.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.base.entity.Pessoa;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = -8233424205129392420L;

	private EntityManager manager;

	@Inject
	public PessoaDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public void persist(Pessoa pessoa){
		manager.persist(pessoa);
	}
	
	public Pessoa merge(Pessoa pessoa){
		return manager.merge(pessoa);
	}

	public Pessoa porId(String id) {
		return manager.find(Pessoa.class, id);
	}
	
	public void remover(Pessoa pessoa) {
		this.manager.remove(pessoa);
	}

	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}

}
