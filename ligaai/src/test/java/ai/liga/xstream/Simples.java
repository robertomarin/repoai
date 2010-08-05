package ai.liga.xstream;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("simples_assim")
public class Simples {

	private final String nome = "Um nome";

	@XStreamAlias("apelido")
	private final String apelido = "Um apelido";

	private final List<String> valores = new ArrayList<String>();

	public Simples() {
		valores.add("valor 1");
		valores.add("valor 2");
		valores.add("valor 3");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valores == null) ? 0 : valores.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Simples other = (Simples) obj;
		if (apelido == null) {
			if (other.apelido != null) {
				return false;
			}
		} else if (!apelido.equals(other.apelido)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (valores == null) {
			if (other.valores != null) {
				return false;
			}
		} else if (!valores.equals(other.valores)) {
			return false;
		}
		return true;
	}
	
}
