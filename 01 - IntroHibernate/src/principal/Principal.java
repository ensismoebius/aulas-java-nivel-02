package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Dependente;
import model.Responsavel;

public class Principal {

	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("PUCinema");

		EntityManager gerenciador = fabrica.createEntityManager();

		Dependente d = new Dependente();
		d.setMatricula(1234);
		d.setNome("Uga da Silva Sauro");

		Dependente d2 = new Dependente();
		d2.setMatricula(4321);
		d2.setNome("Marcelo D2");

		Responsavel r = new Responsavel();
		r.setNome("Ronaldinho Gaúcho");
		r.getDependentes().add(d);
		r.getDependentes().add(d2);

		gerenciador.getTransaction().begin();
		gerenciador.persist(r);
		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

		gerenciador = fabrica.createEntityManager();
		gerenciador.getTransaction().begin();

		r.setNome("Misses Uguina");

		gerenciador.merge(r);

		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();
		
		Query q = gerenciador.createQuery("from Responsavel");
		
		List<Responsavel> lista = q.getResultList();
		
		
		for (Responsavel responsavel : lista) {
			
			List<Dependente> dep = responsavel.getDependentes();
			
			for (Dependente dependente : dep) {
				System.out.println(dependente.getNome());
			}
		}

		fabrica.close();
		
	}

}
