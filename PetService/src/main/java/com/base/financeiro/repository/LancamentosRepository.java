package com.base.financeiro.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.base.entity.Lancamento;
import com.base.financeiro.dao.LancamentoDAO;
import com.base.financeiro.exception.NegocioException;
import com.base.financeiro.interceptor.Transactional;

public class LancamentosRepository implements Serializable {
	private static final long serialVersionUID = -5377073863787802479L;

	@Inject
	private LancamentoDAO lancamentoDAO;
	
	public List<String> descricoesQueContem(String descricao) {
		return lancamentoDAO.descricoesQueContem(descricao);
	}

	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentoDAO.guardar(lancamento);
	}

	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentoDAO.porId(lancamento.getId());
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}
		this.lancamentoDAO.remover(lancamento);
	}

	public List<Lancamento> todos() {
		return this.lancamentoDAO.todos();
	}
}
