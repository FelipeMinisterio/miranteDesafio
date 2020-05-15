package br.com.HibernateTest;

import java.util.Date;

import org.hibernate.Session;

import br.com.modelo.Operador;
import br.com.modelo.Perfil;
import br.com.util.HibernateUtil;

public class HibernateTest {

	public static void main(String[] args) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
  
        session.beginTransaction();
        Date date=java.util.Calendar.getInstance().getTime();
        Operador operador = new Operador();
        Perfil perfil = new Perfil();
        perfil.setId_perfil(1);
        operador.setNome("Felipe");
        operador.setLogin("felipexx");
        operador.setId_perfil(1);
        operador.setSenha("1234");
        session.save(operador);
      
        session.getTransaction().commit();
 
    }	
	
	
}
