package br.com.fiap.main;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.fiap.util.Mock;

public class MainCadastro {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("encontro");
		EntityManager em = fabrica.createEntityManager();
		
		new Mock(em);
		
		em.close();
		fabrica.close();
		
	}

}
