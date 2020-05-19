package com.challenge.desafio;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {

	@Override
	public BigDecimal somar(Object objeto) {
		return somaCampos(objeto, Somar.class);
	}

	@Override
	public BigDecimal subtrair(Object objeto) {
		return somaCampos(objeto, Subtrair.class);
	}

	@Override
	public BigDecimal totalizar(Object objeto) {
		return somar(objeto).subtract(subtrair(objeto));
	}

	private BigDecimal somaCampos(Object objeto, Class<? extends Annotation> anotacao) {
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			List<Field> camposComAnotacao = Arrays.asList(objeto.getClass().getDeclaredFields()).stream()
					.filter(campo -> campo.getAnnotation(anotacao) != null && campo.getType() == BigDecimal.class)
					.collect(Collectors.toList());
			if (camposComAnotacao.size() > 0) {
				for (Field campo : camposComAnotacao) {
					campo.setAccessible(true);
					retorno = retorno.add((BigDecimal) campo.get(objeto));
				}
			}
			return retorno;
		} catch (IllegalAccessException | IllegalArgumentException e) {
			return retorno;
		}
	}
}
