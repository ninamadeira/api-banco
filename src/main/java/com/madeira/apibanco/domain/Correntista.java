package com.madeira.apibanco.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.madeira.apibanco.domain.enums.TipoCorrentista;

@Entity
public class Correntista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer tipo;
	private String cpfOuCnpj;
	private Integer score;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "correntista")
	private ContaCorrente contaCorrente;

	public Correntista() {
	}

	public Correntista(Integer id, String nome, TipoCorrentista tipo, String cpfOuCnpj, Integer score) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.cpfOuCnpj = cpfOuCnpj;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoCorrentista getTipo() {
		return TipoCorrentista.toEnum(tipo);
	}

	public void setTipo(TipoCorrentista tipo) {
		this.tipo = tipo.getCod();
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
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
		Correntista other = (Correntista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
