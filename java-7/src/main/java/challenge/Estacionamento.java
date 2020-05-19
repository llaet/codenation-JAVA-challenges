package challenge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Estacionamento {

	private List<Carro> carros;

	public Estacionamento() {
		carros = new ArrayList<>();
	}

	public void estacionar(Carro carro) {
		if (carros.size() < 10) {
			carros.add(carro);
		} else {
			boolean tentativaRetirarCarro = retiraCarroFila();
			if (tentativaRetirarCarro == true) {
				carros.add(carro);
			} else {
				throw new EstacionamentoException("");
			}
		}
	}

	public boolean retiraCarroFila() {
		if (carros.size() > 0) {
			Iterator<Carro> iterador = carros.iterator();
			while (iterador.hasNext()) {
				Carro carro = iterador.next();
				if (carro.getMotorista().getIdade() < 55) {
					carros.remove(carro);
					return true;
				}
			}
		}
		return false;
	}

	public int carrosEstacionados() {
		return carros.size();
	}

	public boolean carroEstacionado(Carro carro) {
		return carros.contains(carro);
	}

}
