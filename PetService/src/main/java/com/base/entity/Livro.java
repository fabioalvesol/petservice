package com.base.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
@Access(AccessType.FIELD)
public class Livro extends AppEntity {
	private static final long serialVersionUID = -7520513263678154629L;

	private String titulo;
	private String autor;

	public Livro() {
	}

	public Livro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
