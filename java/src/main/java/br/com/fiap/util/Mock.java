package br.com.fiap.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.domain.Condominio;
import br.com.fiap.domain.Estado;
import br.com.fiap.domain.Estrutura;
import br.com.fiap.domain.Morada;
import br.com.fiap.domain.Morador;
import br.com.fiap.domain.Ocupacao;
import br.com.fiap.domain.Prestador;
import br.com.fiap.domain.Registro;
import br.com.fiap.domain.Servico;

public class Mock {
	
	public Mock(EntityManager em) {
		
		// Incluindo condominios 
		
				Condominio condominio1 = new Condominio(0,
				"Condomínio do Edifício Yellow Bali",
				"Av. Alfredo Balthazar da Silveira, 289 - bloco 2 - Recreio dos Bandeirantes, Rio de Janeiro - RJ, 22790-710",
				null);
				Condominio condominio2 = new Condominio(0,
				"Condomínio London Green",
				"R. César Lattes, 1000 - Barra da Tijuca, Rio de Janeiro - RJ, 22793-329",
				null);
				
				Condominio condominio3 = new Condominio(0,
				"Vila Pan-Americana",
				"Av. Cláudio Besserman Vianna - Jacarepaguá, Rio de Janeiro - RJ, 22775-036",
				null);
				
				// Incluindo moradas
				
				Morada morada1 = new Morada(0,55,Estrutura.CASA,null,condominio2);
				
				Morada morada2 = new Morada(0,104,Estrutura.APARTAMENTO,null,condominio3);
				
				Morada morada3 = new Morada(0,204,Estrutura.APARTAMENTO,null,condominio1);
				
				Morada morada4 = new Morada(0,105,Estrutura.APARTAMENTO,null,condominio3);
				
				Morada morada5 = new Morada(0,70,Estrutura.CASA,null,condominio2);
				
				List<Morada> moradas1 = new ArrayList<Morada>();
				moradas1.add(morada1);
				
				List<Morada> moradas2 = new ArrayList<Morada>();
				moradas2.add(morada2);
				
				List<Morada> moradas3 = new ArrayList<Morada>();
				moradas3.add(morada3);
				
				List<Morada> moradas4 = new ArrayList<Morada>();
				moradas4.add(morada4);
				moradas4.add(morada5);
				
				// Incluindo moradores
				
				Morador morador1 = new Morador(0,"Mario",moradas1,null);
				Morador morador2 = new Morador(0,"Joana",moradas2,null);
				Morador morador3 = new Morador(0,"Isadora",moradas3,null);
				Morador morador4 = new Morador(0,"David",moradas4,null);
				

				// Incluindo servicos
				
				Servico servico1 = new Servico(0,Ocupacao.ELETRICISTA,null);
				Servico servico2 = new Servico(0,Ocupacao.ENCANADOR,null);
				Servico servico3 = new Servico(0,Ocupacao.PINTOR,null);
				Servico servico4 = new Servico(0,Ocupacao.PEDREIRO,null);
				
				List<Servico> servicos1 = new ArrayList<Servico>();
				servicos1.add(servico1);
				servicos1.add(servico2);
				
				List<Servico> servicos2 = new ArrayList<Servico>();
				servicos2.add(servico3);
				servicos2.add(servico4);
				
				// Incluindo prestadores
				
				Prestador prestador1 = new Prestador(0,"José",24477155,servicos1);
				Prestador prestador2 = new Prestador(0,"Cleiton",24277155,servicos2);
				
				//Incluindo registros
				
				Registro registro1 = new Registro(0,morador1,prestador1,Estado.ABERTO);
				Registro registro2 = new Registro(0,morador2,prestador1,Estado.ABERTO);
				Registro registro3 = new Registro(0,morador3,prestador2,Estado.ABERTO);
				Registro registro4 = new Registro(0,morador4,prestador2,Estado.ABERTO);
				
				// Salvando registros
				
				try {
					em.persist(registro1);
					em.persist(registro2);
					em.persist(registro3);
					em.persist(registro4);
					
					
					em.getTransaction().begin();
					em.getTransaction().commit();
				}catch(Exception e) {
					if(em.getTransaction().isActive()) {
						em.getTransaction().rollback();
					}
				}
		
	}

}
