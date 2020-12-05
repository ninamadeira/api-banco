package com.madeira.apibanco.dto;

import java.io.Serializable;

import com.madeira.apibanco.domain.ContaCorrente;

public class ContaCorrenteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer numero;
	private String agencia;
	private String tipo;
	private String faixaScore;

	public ContaCorrenteDTO() {
	}

	public ContaCorrenteDTO(ContaCorrente obj) {
		id = obj.getId();
		numero = obj.getNumero();
		agencia = obj.getAgencia();
		tipo = obj.getTipo();
		faixaScore = obj.getFaixaScore();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFaixaScore() {
		return faixaScore;
	}

	public void setFaixaScore(String faixaScore) {
		this.faixaScore = faixaScore;
	}

}
