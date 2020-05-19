package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private ArrayList<Time> times;
	private RankingJogadores rank;

	public DesafioMeuTimeApplication() {
		times = new ArrayList<>();
		rank = new RankingJogadores();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		validaString(new String[] { nome, corUniformePrincipal, corUniformeSecundario });
		validaLong(new Long[] { id });
		if (buscaTime(id) != null) {
			throw new IdentificadorUtilizadoException("ID já existente!");
		}
		if (dataCriacao == null) {
			throw new NullPointerException("");
		}
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		validaString(new String[] { nome });
		validaLong(new Long[] { id, idTime });
		if (buscaJogador(id) != null) {
			throw new IdentificadorUtilizadoException("ID já existente!");
		}
		if (nivelHabilidade < 0 || nivelHabilidade > 100 || salario.floatValue() < 0) {
			throw new IllegalArgumentException("");
		}
		if (nivelHabilidade == null || dataNascimento == null || salario == null) {
			throw new NullPointerException("");
		}
		Time time = buscaTime(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
		time.setJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
		rank.setJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		validaLong(new Long[] { idJogador });
		Jogador jogador = buscaJogador(idJogador);
		if (jogador != null) {
			buscaTime(jogador.getIDTime()).setCapitao(idJogador);
		} else {
			throw new JogadorNaoEncontradoException("ID não encontrado!");
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			if (time.getCapitao() != null) {
				return time.getCapitao();
			} else {
				throw new CapitaoNaoInformadoException("Capitão do time não existente");
			}
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		validaLong(new Long[] { idJogador });
		Jogador jogador = buscaJogador(idJogador);
		if (jogador != null) {
			return jogador.getNOME();
		} else {
			throw new JogadorNaoEncontradoException("ID não encontrado!");
		}
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			return time.getNOME();
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			List<Jogador> jogadores = time.getJogadores();
			List<Long> jogadoresIDOrdenados = new ArrayList<>();
			for (Jogador nomes : jogadores) {
				jogadoresIDOrdenados.add(nomes.getID());
			}
			Collections.sort(jogadoresIDOrdenados);
			return jogadoresIDOrdenados;
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			return time.getMelhorJogador();
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			return time.getJogadorMaisVelho();
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		if (times.isEmpty()) {
			return new ArrayList<Long>();
		} else {
			List<Long> timesID = new ArrayList<>();
			for (Time time : times) {
				timesID.add(time.getID());
			}
			Collections.sort(timesID);
			return timesID;
		}
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		validaLong(new Long[] { idTime });
		Time time = buscaTime(idTime);
		if (time != null) {
			return time.getJogadorMaiorSalario();
		} else {
			throw new TimeNaoEncontradoException("ID do time não encontrado!");
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		validaLong(new Long[] { idJogador });
		Jogador jogador = buscaJogador(idJogador);
		if (jogador != null) {
			return jogador.getSALARIO();
		} else {
			throw new JogadorNaoEncontradoException("ID não encontrado!");
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		validaString(new String[] { top.toString() });
		if (rank.getNumeroJogadores() >= top) {
			return rank.getRanking(top);
		} else if (rank.getNumeroJogadores() == 0) {
			return rank.getRanking(top);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		validaLong(new Long[] { timeDaCasa, timeDeFora });
		Time corTimeCasa = buscaTime(timeDaCasa);
		Time corTimeFora = buscaTime(timeDeFora);
		if (corTimeCasa != null && corTimeFora != null) {
			if (corTimeCasa.getUNIFORME_PRINCIPAL().equals(corTimeFora.getUNIFORME_PRINCIPAL())) {
				return corTimeFora.getUNIFORME_SECUNDARIO();
			}
			return corTimeFora.getUNIFORME_PRINCIPAL();
		} else {
			throw new TimeNaoEncontradoException("ID time não encontrado!");
		}
	}

	private Time buscaTime(Long idTimeProcurado) {
		for (Time time : times) {
			if (time.getID() == idTimeProcurado) {
				return time;
			}
		}
		return null;
	}

	private Jogador buscaJogador(Long idJogadorProcurado) {
		for (Time time : times) {
			if (time.buscarJogador(idJogadorProcurado) != null) {
				return time.buscarJogador(idJogadorProcurado);
			}
		}
		return null;
	}

	private void validaString(String[] parametros) {
		for (String parametro : parametros) {
			if (parametro == null) {
				throw new NullPointerException("Parâmetro obrigatório não poderá ser vazio!");
			}
			if (parametro.isEmpty()) {
				throw new IllegalArgumentException("Parâmetro obrigatório não poderá ser vazio!");
			}
		}
	}

	private void validaLong(Long[] parametros) {
		for (Long parametro : parametros) {
			if (parametro == null) {
				throw new NullPointerException("Parâmetro obrigatório não poderá ser vazio!");
			}
			if (parametro < 0) {
				throw new IllegalArgumentException("Parâmetro obrigatório não poderá ser vazio!");
			}
		}
	}
}
