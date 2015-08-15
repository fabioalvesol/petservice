package com.base.financeiro.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.base.discreto.TipoLancamento;
import com.base.entity.Lancamento;
import com.base.entity.Pessoa;
import com.base.financeiro.exception.NegocioException;
import com.base.financeiro.repository.LancamentosRepository;
import com.base.financeiro.repository.PessoasRepository;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 5252858305673450274L;

	@Inject
	private LancamentosRepository lancamentosRepository;

	@Inject
	private PessoasRepository pessoasRepository;

	private Lancamento lancamento = new Lancamento();

	private List<Pessoa> todasPessoas;

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentosRepository.descricoesQueContem(descricao);
	}
	
	public void prepararCadastro() {
		this.todasPessoas = pessoasRepository.todos();
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}

	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Novo valor: " + event.getNewValue());
	}

	// chamado pelo listener
	public void registrarLogCadastro(ActionEvent event) {
		System.out.println("Cadastrando...");
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.lancamentosRepository.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}
