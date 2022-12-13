package aplicacion.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import aplicacion.modelo.Producto;
import aplicacion.modelo.Usuario;
import aplicacion.utils.JPAUtil;

public class UsuarioDAO {
	

	/**
	 * Crea un objeto de tipo Usuario en la BBDD.
	 * @param usu Objeto de tipo Usuario.
	 */
	public void createUsuario(Usuario usu) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
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
	
	/**
	 * Metodo que lee la tabla Usuarios de la BBDD.
	 * @return Devuele una collecion de objetos de tipo Usuario.
	 */
	public ArrayList<Usuario> readUsuario() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			@SuppressWarnings("unchecked")
			ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) em.createQuery("from Usuario").getResultList();
			em.getTransaction().commit();
			return listaUsuarios;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}
	
	/**
	 * Metodo que actualiza el nombre de usuario.
	 * @param id Id de Usuario.
	 * @param usu Nombre de usuario nuevo.
	 */
	public void updateUsuarioUsu(int id, String usu) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setUsu(usu);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que actualiza los nombres y apellidos del usuario.
	 * @param id ID del usuario.
	 * @param nomApe Nombre y apellidos nuevos del usuario.
	 */
	public void updateUsuarioNomApe(int id, String nomApe) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setNombreApellidos(nomApe);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que actualiza la password del usuario.
	 * @param id ID del usuario.
	 * @param pass Password nuevo del usuario.
	 */
	public void updateUsuarioPassword(int id, String pass) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setPassword(pass);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que actualiza el correo electrinico del usuario.
	 * @param id ID del usuario.
	 * @param correo Correo nuevo del usuario.
	 */
	public void updateUsuarioCorreo(int id, String correo) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setEmail(correo);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que actualiza la direccion del usuario.
	 * @param id ID del usuario.
	 * @param direc Direccion nueva del usuario.
	 */
	public void updateUsuarioDireccion(int id, String direc) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setDireccion(direc);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que actualiza el rol del Usuario.
	 * @param id ID del usuario.
	 * @param rol Rol nuevo del usuario, 0: Admin, 1:Estandar.
	 */
	public void updateUsuarioRol(int id, int rol) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setRol(rol);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	/**
	 * Metodo que actualiza los parametros de un usuario mediante un objeto Usuario.
	 * @param id ID del usuario.
	 * @param usuNew Usuario nuevo.
	 */
	public void updateUsuario(int id, Usuario usuNew) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			u.setUsu(usuNew.getUsu());
			u.setNombreApellidos(usuNew.getNombreApellidos());
			u.setPassword(usuNew.getPassword());
			u.setDireccion(usuNew.getDireccion());
			u.setUsu(usuNew.getUsu());
			u.setEmail(usuNew.getEmail());
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	/**
	 * Borrar un usuario de la BBDD.
	 * @param id ID del usuario.
	 */
	public void deleteUsuario(int id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			em.remove(em.contains(u)? u:em.merge(u));
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Busca un objeto de tipo Usuario segun su ID.
	 * @param id int ID del usuario.
	 * @return Usuario.
	 */
	public Usuario buscaUsuario(Integer id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Usuario u = em.find(Usuario.class, id);
			return u;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}
	
}
