package br.com.codenation;

import java.time.LocalDate;

public class Identificacao {

	private final long ID;
	private final String NOME;
	private final LocalDate DATA;

	public Identificacao(Long id, String nome, LocalDate dataCriacao) {
		ID = id;
		NOME = nome;
		DATA = dataCriacao;
	}

	public long getID() {
		return ID;
	}

	public String getNOME() {
		return NOME;
	}

	public LocalDate getDATA() {
		return DATA;
	}

}
