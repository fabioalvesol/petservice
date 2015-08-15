package com.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.base.entity.Pessoa;
import com.base.financeiro.dao.PessoaDAO;
import com.base.financeiro.util.CDILocator;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	// @Inject (ainda não é suportado)
	private PessoaDAO pessoas;

	public PessoaConverter() {
		this.pessoas = CDILocator.getBean(PessoaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Pessoa retorno = null;

		if (value != null) {
			retorno = this.pessoas.porId(new String(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Pessoa) value).getId().toString();
		}
		return null;
	}

}
