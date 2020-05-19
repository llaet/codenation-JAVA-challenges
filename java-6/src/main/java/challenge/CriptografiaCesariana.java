package challenge;

public class CriptografiaCesariana implements Criptografia {

	@Override
	public String criptografar(String texto) {
		illegalOrNullText(texto);
		StringBuilder sb = new StringBuilder();
		texto = texto.toLowerCase();
		for (char index : texto.toCharArray()) {
			switch (index) {
			case 'x':
				sb.append((char) 97);
				break;
			case 'y':
				sb.append((char) 98);
				break;
			case 'z':
				sb.append((char) 99);
				break;
			default:
				if (((int) index) <= 57 && ((int) index) >= 48) {
					sb.append(index);
				} else {
					sb.append((char) (((int) index) + 3));
				}
			}
		}
		return sb.toString().replace("#", " ");
	}

	@Override
	public String descriptografar(String texto) {
		illegalOrNullText(texto);
		StringBuilder sb = new StringBuilder();
		texto = texto.toLowerCase();
		for (char index : texto.toCharArray()) {
			switch (index) {
			case 'a':
				sb.append((char) 120);
				break;
			case 'b':
				sb.append((char) 121);
				break;
			case 'c':
				sb.append((char) 122);
				break;
			case ' ':
				sb.append(index);
				break;
			default:
				if (((int) index) <= 57 && ((int) index) >= 48) {
					sb.append(index);
				} else {
					sb.append((char) (((int) index) - 3));
				}
			}
		}
		return sb.toString();
	}

	private void illegalOrNullText(String text) {
		if (text.isEmpty())
			throw new IllegalArgumentException();
		if (text == null)
			throw new NullPointerException();
	}
}
