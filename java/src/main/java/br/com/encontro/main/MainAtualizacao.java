package br.com.encontro.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.encontro.util.Mock;
import br.com.fiap.domain.Condominio;

public class MainAtualizacao {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("encontro");
		EntityManager em = fabrica.createEntityManager();
		
		new Mock(em);
		
		Condominio condominio = em.find(Condominio.class, 1);
		
		System.out.println();
		System.out.println("NOME CONDOMINIO ANTIGO:");
		System.out.println(condominio.getNome());
		System.out.println();
		System.out.println("ENDERE�O CONDOMINIO ANTIGO:");
		System.out.println(condominio.getEndereco());
		System.out.println();
		System.out.println("-------------------------------------------------------");
		
		Condominio novoCondominio = new Condominio(1,
				"Condom�nio Lagoa do Itanhang�",
				"Estr. do Itanhang�, 2222 - Itanhang�, Rio de Janeiro - RJ, 22753-005",null);
		
		em.merge(novoCondominio);
		
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
		/*condominio.setNome("Condom�nio Lagoa do Itanhang�");
		condominio.setEndereco("Estr. do Itanhang�, 2222 - Itanhang�, Rio de Janeiro - RJ, 22753-005");*/
		
		System.out.println();
		System.out.println("NOME CONDOMINIO NOVO:");
		System.out.println(condominio.getNome());
		System.out.println();
		System.out.println("ENDERE�O CONDOMINIO NOVO:");
		System.out.println(condominio.getEndereco());
		System.out.println();
		
		em.close();
		fabrica.close();

	}

}
