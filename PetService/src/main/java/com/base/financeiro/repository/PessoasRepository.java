package com.base.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.base.entity.Pessoa;
import com.base.financeiro.dao.PessoaDAO;
import com.base.financeiro.exception.NegocioException;
import com.base.financeiro.interceptor.Transactional;

public class PessoasRepository implements Serializable {
	private static final long serialVersionUID = -5377073863787802479L;

	@Inject
	private PessoaDAO pessoaDAO;

	@Transactional
	public void salvar(Pessoa pessoa) throws NegocioException {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
			throw new NegocioException("Nome de pessoa é obrigatório.");
		}

		if (pessoa.getId() != null && !pessoa.getId().isEmpty()) {
			this.pessoaDAO.merge(pessoa);
		} else {
			this.pessoaDAO.persist(pessoa);
		}
	}

	@Transactional
	public void excluir(Pessoa pessoa) throws NegocioException {
		pessoa = this.pessoaDAO.porId(pessoa.getId());
		this.pessoaDAO.remover(pessoa);
	}

	public List<Pessoa> todos() {
		return this.pessoaDAO.todas();
	}
}
