package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BancoDeDados {
	 private static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("PUCinema");
	 
	 public static EntityManager criarGerenciador() {
		 return fabrica.createEntityManager();
	 }

	public static void fechar() {
		fabrica.close();		
	}
	 
}
