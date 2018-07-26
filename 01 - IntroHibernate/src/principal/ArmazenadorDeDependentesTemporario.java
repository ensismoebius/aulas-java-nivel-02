package principal;

import java.util.ArrayList;
import java.util.List;

import model.Dependente;

public class ArmazenadorDeDependentesTemporario {

	private static List<Dependente> lista;

	public static void addDependente(Dependente d) {
		if (lista == null) {
			lista = new ArrayList<Dependente>();
		}
		lista.add(d);
	}

	public static List<Dependente> getLista() {
		return lista;
	}
	
	public static void limpar() {
		lista.clear();
	}

}
