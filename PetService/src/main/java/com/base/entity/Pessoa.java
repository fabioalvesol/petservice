package com.base.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "pessoa")
@Access(AccessType.FIELD)
@NamedQueries(@NamedQuery(name = Pessoa.findByNome, query = "Select obj from Pessoa obj where obj.nome = :nome"))
public class Pessoa extends AppEntity {
	private static final long serialVersionUID = 4941476435488852594L;

	public static final String findByNome = "Pessoa.findByNome";

	@NotEmpty
	@Size(max = 60)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
