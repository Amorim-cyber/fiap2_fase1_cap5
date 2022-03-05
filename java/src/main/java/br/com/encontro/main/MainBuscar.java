package br.com.encontro.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.encontro.entity.Morador;
import br.com.encontro.util.Mock;

public class MainBuscar {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("encontro");
		EntityManager em = fabrica.createEntityManager();
		
		new Mock(em);
		
		Morador morador1 = em.find(Morador.class, 1);
		Morador morador2 = em.find(Morador.class, 2);
		Morador morador3 = em.find(Morador.class, 3);
		Morador morador4 = em.find(Morador.class, 4);
		
		System.out.println("ID\tNOME");
		System.out.println(morador1.getId() + "\t" +morador1.getNome());
		System.out.println(morador2.getId() + "\t" +morador2.getNome());
		System.out.println(morador3.getId() + "\t" +morador3.getNome());
		System.out.println(morador4.getId() + "\t" +morador4.getNome());
		
		
		em.close();
		fabrica.close();

	}

}
