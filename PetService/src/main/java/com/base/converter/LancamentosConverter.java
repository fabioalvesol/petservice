package com.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.base.entity.Lancamento;
import com.base.financeiro.dao.LancamentoDAO;
import com.base.financeiro.util.CDILocator;

@FacesConverter(forClass = Lancamento.class)
public class LancamentosConverter implements Converter {
	// @Inject (ainda não é suportado)
	private LancamentoDAO lancamentos;

	public LancamentosConverter() {
		this.lancamentos = CDILocator.getBean(LancamentoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		if (value != null) {
			retorno = this.lancamentos.porId(new String(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento lancamento = ((Lancamento) value);
			return lancamento.getId() == null ? null : lancamento.getId().toString();
		}
		return null;
	}
}