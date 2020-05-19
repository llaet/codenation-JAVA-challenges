package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador extends Identificacao {

	private final long ID_TIME;
	private final int NIVEL_HABILIDADE;
	private final BigDecimal SALARIO;

	public Jogador(Long id, Long idTime, String nome, LocalDate dataCriacao, Integer nivelHabilidade,
			BigDecimal salario) {
		super(id, nome, dataCriacao);
		ID_TIME = idTime;
		NIVEL_HABILIDADE = nivelHabilidade;
		SALARIO = salario;
	}

	public long getIDTime() {
		return ID_TIME;
	}

	public int getNIVEL_HABILIDADE() {
		return NIVEL_HABILIDADE;
	}

	public BigDecimal getSALARIO() {
		return SALARIO;
	}
}
