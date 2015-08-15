package com.base.financeiro.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.base.entity.Lancamento;
import com.base.financeiro.exception.NegocioException;
import com.base.financeiro.repository.LancamentosRepository;

@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = -6948119726671004198L;

	@Inject
	private LancamentosRepository lancamentosRepository;

	private List<Lancamento> lancamentos;

	private Lancamento lancamentoSelecionado;

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.lancamentosRepository.excluir(this.lancamentoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void consultar() {
		this.lancamentos = this.lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}