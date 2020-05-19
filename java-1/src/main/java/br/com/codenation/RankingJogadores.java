package br.com.codenation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RankingJogadores {

	private ArrayList<Jogador> jogadores;

	public RankingJogadores() {
		jogadores = new ArrayList<>();
	}

	public int getNumeroJogadores() {
		return jogadores.size();
	}

	public void setJogador(Jogador jogador) {
		jogadores.add(jogador);
	}

	public List<Long> getRanking(int numeroDeJogadores) {
		return jogadores.stream()
				.sorted(Comparator.comparingInt(Jogador::getNIVEL_HABILIDADE).reversed().thenComparing(Jogador::getID))
				.limit(numeroDeJogadores).map(Jogador::getID).collect(Collectors.toList());
	}
}
