package mDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import models.Usuario;

import utils.JPAUtil;

public class UsuarioDAO {

	public void createUsuario(Usuario usu) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		//Crear transaccion
		em.getTransaction().begin();
		try {
			em.persist(usu);
			em.getTransaction().commit();
		} 
		catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		}
		finally {
			em.close();
		}
	}

}
