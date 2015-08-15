package com.base.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

@MappedSuperclass
@Where(clause = "dataExclusao IS NULL")
public class AppEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public AppEntity(String id) {
		this.id = id;
	}

	public AppEntity() {
	}

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataUltAtualizacao;

	private Long versao;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;

	@PrePersist
	private void gerarUUID() {
		this.setId(UUID.randomUUID().toString());
		atualizarDataVersao();
	}

	@PreUpdate
	private void atualizarDataVersao() {
		// atualiza para data atual
		this.setDataUltAtualizacao(Calendar.getInstance());
		// incrementa versão
		if (this.getVersao() == null) {
			this.setVersao(new Long(0L));
		} else {
			this.setVersao(this.getVersao() + 1);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getDataUltAtualizacao() {
		return dataUltAtualizacao;
	}

	public void setDataUltAtualizacao(Calendar dataUltAtualizacao) {
		this.dataUltAtualizacao = dataUltAtualizacao;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public Calendar getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppEntity other = (AppEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
