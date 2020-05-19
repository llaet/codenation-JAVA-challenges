package br.com.codenation;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplicationTest {

	private DesafioMeuTimeApplication timeApplicationTestes;

	@Before
	public void before() {
		timeApplicationTestes = new DesafioMeuTimeApplication();
	}

	@Test(expected = IdentificadorUtilizadoException.class)
	public void deveRetornarErroQuandoRepeteIdTime() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void deveRetornarErroQuandoTimeNaoExiste() {
		timeApplicationTestes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 56, new BigDecimal(1.25));
	}

	@Test(expected = IdentificadorUtilizadoException.class)
	public void deveRetornarErroQuandoRepeteIdJogador() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "Juca", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(1L, 1L, "Juca", LocalDate.now(), 56, new BigDecimal(1.25));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRetornarErroQuandoHabilidadeMaior100() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "jao", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(2L, 1L, "Ze", LocalDate.now(), 101, new BigDecimal(1.25));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRetornarErroQuandoHabilidadeMenor0() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "Juca Pires", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(2L, 1L, "Pedro", LocalDate.now(), -1, new BigDecimal(1.25));
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void deveRetornarErroQuandoNaoExisteJogador() {
		timeApplicationTestes.incluirTime(1L, "Flamengo", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "Jefferson", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.definirCapitao(2L);
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void deveRetornarErroQuandoBuscaCapitaoENaoExisteTime() {
		timeApplicationTestes.incluirTime(1L, "Vasco", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "Pedro Bola", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.definirCapitao(1L);
		timeApplicationTestes.buscarCapitaoDoTime(2L);
	}

	@Test(expected = CapitaoNaoInformadoException.class)
	public void deveRetornarErroQuandoBuscaCapitaoENaoHaCapitao() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(1L, 1L, "jao", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.buscarCapitaoDoTime(1L);
	}

	@Test
	public void deveRetornarIdCapitao() {
		timeApplicationTestes.incluirTime(1L, "Cruzeiro", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(3L, 1L, "Henrique", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.definirCapitao(3L);
		assertEquals(new Long(3), timeApplicationTestes.buscarCapitaoDoTime(1L));
	}

	@Test(expected = JogadorNaoEncontradoException.class)
	public void deveRetornarErroQuandoBuscaNomeDeJogadorInexistente() {
		timeApplicationTestes.buscarNomeJogador(1L);
	}

	@Test
	public void deveRetornarNomeJogador() {
		timeApplicationTestes.incluirTime(1L, "Palmeiras", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(3L, 1L, "Fulano", LocalDate.now(), 56, new BigDecimal(1.25));
		assertEquals("Fulano", timeApplicationTestes.buscarNomeJogador(3L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void deveRetornarErroQuandoBuscaNomeDeTimeInexistente() {
		timeApplicationTestes.buscarNomeTime(1L);
	}

	@Test
	public void deveRetornarNomeTime() {
		timeApplicationTestes.incluirTime(1L, "Vasco", LocalDate.now(), "preto", "branco");
		assertEquals("Vasco", timeApplicationTestes.buscarNomeTime(1L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void deveRetornarErroQuandoBuscaJogadoresDeTimeInexistente() {
		timeApplicationTestes.buscarJogadoresDoTime(1L);
	}

	@Test
	public void deveRetornarListaOrdenadaJogadores() {
		timeApplicationTestes.incluirTime(1L, "Vasco da Gama", LocalDate.now(), "Vermelho", "Branco");
		timeApplicationTestes.incluirTime(2L, "Flamengo", LocalDate.now(), "Vermelho", "Preto");
		timeApplicationTestes.incluirJogador(15L, 1L, "Jota", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(12L, 2L, "Marcos", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(3L, 1L, "Edgar Pernoca", LocalDate.now(), 56, new BigDecimal(1.25));
		List<Long> result = new ArrayList<>(Arrays.asList(3L, 15L));
		List<Long> lista = timeApplicationTestes.buscarJogadoresDoTime(1L);
		for (int i = 0; i < result.size(); i++) {
			assertEquals(result.get(i), lista.get(i));
		}
	}

	@Test
	public void deveRetornarMelhorJogador() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(15L, 1L, "Jota", LocalDate.now(), 56, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(0L, 1L, "Marcos", LocalDate.of(1988, Month.APRIL, 25), 9,
				new BigDecimal(50000));
		timeApplicationTestes.incluirJogador(1L, 1L, "Cristofer", LocalDate.of(1990, Month.AUGUST, 1), 10,
				new BigDecimal(40000));
		timeApplicationTestes.incluirJogador(8L, 1L, "Josue", LocalDate.now(), 91, new BigDecimal(1.25));
		assertEquals(new Long(8L), timeApplicationTestes.buscarMelhorJogadorDoTime(1L));
	}

	@Test
	public void deveRetornarJogadorMaisVelho() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(2L, 1L, "Robertinho", LocalDate.of(1992, Month.DECEMBER, 11), 7,
				new BigDecimal(55000));
		timeApplicationTestes.incluirJogador(1L, 1L, "Jilson Pernoca", LocalDate.of(1990, Month.AUGUST, 1), 8,
				new BigDecimal(60000));
		timeApplicationTestes.incluirJogador(3L, 1L, "Jota", LocalDate.of(1987, 1, 1), 89, new BigDecimal(1.25));
		timeApplicationTestes.incluirJogador(8L, 1L, "Cristofer", LocalDate.of(1987, 1, 1), 91, new BigDecimal(1.25));
		assertEquals(new Long(3L), timeApplicationTestes.buscarJogadorMaisVelho(1L));
	}

	@Test
	public void deveRetornarListaVaziaTimes() {
		assertEquals(new ArrayList<Long>(), timeApplicationTestes.buscarTimes());
	}

	@Test
	public void deveRetornarListaOrdenadaTimes() {
		timeApplicationTestes.incluirTime(35L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(7L, "time C", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(5L, "time D", LocalDate.now(), "branco", "azul");
		assertEquals(new ArrayList<Long>(Arrays.asList(2L, 5L, 7L, 35L)), timeApplicationTestes.buscarTimes());
	}

	@Test
	public void deveRetornarJogadorMaiorSalario() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(15L, 1L, "Jota", LocalDate.of(1990, 3, 5), 56, BigDecimal.valueOf(1357.25));
		timeApplicationTestes.incluirJogador(12L, 2L, "Edgar", LocalDate.of(1900, 1, 1), 56,
				BigDecimal.valueOf(10000000.25));
		timeApplicationTestes.incluirJogador(3L, 1L, "Jilson", LocalDate.of(1987, 1, 1), 89, BigDecimal.valueOf(13357.25));
		timeApplicationTestes.incluirJogador(8L, 1L, "Lucas", LocalDate.of(1987, 1, 1), 91, BigDecimal.valueOf(10557.25));
		assertEquals(new Long(3L), timeApplicationTestes.buscarJogadorMaiorSalario(1L));
	}

	@Test
	public void deveRetornarSalarioJogador() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(15L, 1L, "Jota", LocalDate.of(1990, 3, 5), 56, BigDecimal.valueOf(1357.25));
		timeApplicationTestes.incluirJogador(12L, 2L, "Edgar", LocalDate.of(1900, 1, 1), 56,
				BigDecimal.valueOf(10000000.25));
		timeApplicationTestes.incluirJogador(3L, 1L, "Jilson", LocalDate.of(1987, 1, 1), 89, BigDecimal.valueOf(13357.25));
		timeApplicationTestes.incluirJogador(8L, 1L, "Lucas", LocalDate.of(1987, 1, 1), 91, BigDecimal.valueOf(10557.25));
		assertEquals(BigDecimal.valueOf(13357.25), timeApplicationTestes.buscarSalarioDoJogador(3L));
	}

	@Test
	public void deveRetornarLista3TopJogadores() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(15L, 1L, "Jota", LocalDate.of(1990, 3, 5), 56, BigDecimal.valueOf(1357.25));
		timeApplicationTestes.incluirJogador(12L, 2L, "Bruno", LocalDate.of(1900, 1, 1), 100,
				BigDecimal.valueOf(10000000.25));
		timeApplicationTestes.incluirJogador(11L, 2L, "Nonato", LocalDate.of(1900, 1, 1), 100,
				BigDecimal.valueOf(10000000.25));
		timeApplicationTestes.incluirJogador(3L, 1L, "Gilson", LocalDate.of(1987, 1, 1), 89, BigDecimal.valueOf(13357.25));
		timeApplicationTestes.incluirJogador(8L, 1L, "Marcos", LocalDate.of(1987, 1, 1), 91, BigDecimal.valueOf(10557.25));
		assertEquals(new ArrayList<>(Arrays.asList(11L, 12L, 8L)), timeApplicationTestes.buscarTopJogadores(3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRetornarIndexOutOfBounds() {
		timeApplicationTestes.incluirTime(1L, "time A", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time B", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirJogador(15L, 1L, "Marcos", LocalDate.of(1990, 3, 5), 56, BigDecimal.valueOf(1357.25));
		timeApplicationTestes.incluirJogador(12L, 2L, "Pirula", LocalDate.of(1900, 1, 1), 53,
				BigDecimal.valueOf(10000000.25));
		timeApplicationTestes.incluirJogador(3L, 1L, "Pluto", LocalDate.of(1987, 1, 1), 89, BigDecimal.valueOf(13357.25));
		timeApplicationTestes.incluirJogador(8L, 1L, "Lucas", LocalDate.of(1987, 1, 1), 91, BigDecimal.valueOf(10557.25));
		timeApplicationTestes.buscarTopJogadores(5);
	}

	@Test
	public void deveRetornarArrayVazio() {
		assertEquals(new ArrayList<Long>(), timeApplicationTestes.buscarTopJogadores(3));
	}

	@Test
	public void deveRetornarCamisaSecundariaTimeFora() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time", LocalDate.now(), "branco", "verde");
		assertEquals("verde", timeApplicationTestes.buscarCorCamisaTimeDeFora(1L, 2L));
	}

	@Test
	public void deveRetornarCamisaPrincipalTimeFora() {
		timeApplicationTestes.incluirTime(1L, "time", LocalDate.now(), "branco", "azul");
		timeApplicationTestes.incluirTime(2L, "time", LocalDate.now(), "azul", "verde");
		assertEquals("azul", timeApplicationTestes.buscarCorCamisaTimeDeFora(1L, 2L));
	}

	@Test(expected = TimeNaoEncontradoException.class)
	public void deveRetornarExcecaoSeUmDosTimesNaoExistir() {
		timeApplicationTestes.buscarCorCamisaTimeDeFora(1L, 2L);
	}

}