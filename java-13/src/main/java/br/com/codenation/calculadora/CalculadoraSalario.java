package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < 1039.00) {
			return 0;
		}
		salarioBase = calcularInss(salarioBase);
		salarioBase = calcularIR(salarioBase);
		return Math.round(salarioBase);
	}

	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500.00) {
			salarioBase = (salarioBase / 100) * 92;
		} else if (salarioBase > 1500.00 && salarioBase <= 4000.00) {
			salarioBase = (salarioBase / 100) * 91;
		} else {
			salarioBase = (salarioBase / 100) * 89;
		}
		return salarioBase;
	}

	private double calcularIR(double salarioBase) {
		if (salarioBase > 3000.00 && salarioBase <= 6000.00) {
			salarioBase = (salarioBase / 100) * 92.5;
		} else if (salarioBase > 6000.00) {
			salarioBase = (salarioBase / 100) * 85;
		}
		return salarioBase;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/