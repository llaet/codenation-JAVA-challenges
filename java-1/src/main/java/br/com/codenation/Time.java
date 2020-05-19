package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;

public class Time extends Identificacao {

	private final String UNIFORME_PRINCIPAL;
	private final String UNIFORME_SECUNDARIO;
	private ArrayList<Jogador> jogadores;
	private Long capitao;

	public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		super(id, nome, dataCriacao);
		UNIFORME_PRINCIPAL = corUniformePrincipal;
		UNIFORME_SECUNDARIO = corUniformeSecundario;
		jogadores = new ArrayList<>();
	}

	public Jogador buscarJogador(Long id) {
		for (Jogador jogador : jogadores) {
			if (jogador.getID() == id) {
				return jogador;
			}
		}
		return null;
	}

	public void setJogador(Jogador jogador) {
		jogadores.add(jogador);
	}

	public void setCapitao(Long id) {
		capitao = id;
	}

	public Long getMelhorJogador() {
		if (!(jogadores.isEmpty())) {
			Jogador melhorJogador = jogadores.get(0);
			for (Jogador jogador : jogadores) {
				if (jogador.getNIVEL_HABILIDADE() > melhorJogador.getNIVEL_HABILIDADE()) {
					melhorJogador = jogador;
				} else if (jogador.getNIVEL_HABILIDADE() == melhorJogador.getNIVEL_HABILIDADE()) {
					if (jogador.getID() < melhorJogador.getID()) {
						melhorJogador = jogador;
					}
				}
			}
			return melhorJogador.getID();
		} else {
			throw new NullPointerException("Não existem jogadores registrados neste time!");
		}
	}

	public Long getJogadorMaisVelho() {
		if (!(jogadores.isEmpty())) {
			Jogador jogadorMaisVelho = jogadores.get(0);
			for (Jogador jogador : jogadores) {
				if (jogador.getDATA().isBefore(jogadorMaisVelho.getDATA())) {
					jogadorMaisVelho = jogador;
				} else if (jogador.getDATA().isEqual(jogadorMaisVelho.getDATA())) {
					if (jogador.getID() < jogadorMaisVelho.getID()) {
						jogadorMaisVelho = jogador;
					}
				}
			}
			return jogadorMaisVelho.getID();
		} else {
			throw new NullPointerException("Não existem jogadores registrados neste time!");
		}
	}

	public Long getJogadorMaiorSalario() {
		if (!(jogadores.isEmpty())) {
			Jogador jogadorMaiorSalario = jogadores.get(0);
			for (Jogador jogador : jogadores) {
				if (jogador.getSALARIO().compareTo(jogadorMaiorSalario.getSALARIO()) > 0) {
					jogadorMaiorSalario = jogador;
				} else if (jogador.getSALARIO().compareTo(jogadorMaiorSalario.getSALARIO()) == 0) {
					if (jogador.getID() < jogadorMaiorSalario.getID()) {
						jogadorMaiorSalario = jogador;
					}
				}
			}
			return jogadorMaiorSalario.getID();
		} else {
			throw new NullPointerException("Não existem jogadores registrados neste time!");
		}
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public Long getCapitao() {
		return capitao;
	}

	public String getUNIFORME_PRINCIPAL() {
		return UNIFORME_PRINCIPAL;
	}

	public String getUNIFORME_SECUNDARIO() {
		return UNIFORME_SECUNDARIO;
	}
}
