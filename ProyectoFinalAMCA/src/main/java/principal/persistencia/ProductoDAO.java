package principal.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import principal.models.Producto;
import principal.utils.JPAUtil;

public class ProductoDAO {

	// CRUD: Create - Read - Update - Delete

	/**
	 * Crea un objeto de tipo Producto en la BBDD.
	 * @param p Objeto de tipo Producto.
	 */
	public void createProducto(Producto p) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(p);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}

	/**
	 * Devuelve una coleccion de objetos de tipo Producto al leer la tabla Producto.
	 * 
	 * @return ArrayList<Producto> listaProductos.
	 */
	public ArrayList<Producto> readProducto() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Producto> listaProductos = (ArrayList<Producto>) em.createQuery("from Producto").getResultList();
			em.getTransaction().commit();
			return listaProductos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}

	/**
	 * Metodo que obtiene un producto segun su id y actualiza el precio segun el pasado por parametro.
	 * @param id ID del producto.
	 * @param precio Nuevo precio del producto.
	 */
	public void updateProductoPrecio(int id, double precio) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Producto p = em.find(Producto.class, id);
			p.setPrecio(precio);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que obtiene un producto segun su id y actualiza el stock segun el pasado por parametro.
	 * @param id Id del producto.
	 * @param stock Nuevo stock del producto.
	 */
	public void updateProductoStock(int id, int stock) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Producto p = em.find(Producto.class, id);
			p.setStock(stock);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que obtiene un producto segun su id y actualiza el nombre segun el pasado por parametro.
	 * @param id Id del producto.
	 * @param nombre Nuevo nombre del producto.
	 */
	public void updateProductoNombre(int id, String nombre) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Producto p = em.find(Producto.class, id);
			p.setNombre(nombre);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que otiene un producto segun su id y lo actualiza segun el nuevo objeto de tipo Producto pasado por parametro.
	 * @param id ID del producto.
	 * @param pNew Nuevo objeto de tipo Producto.
	 */
	public void updateProducto(int id, Producto pNew) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Producto p = em.find(Producto.class, id);
			p.setNombre(pNew.getNombre());
			p.setStock(pNew.getStock());
			p.setPrecio(pNew.getPrecio());
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo para borrar un objeto de tipo Producto de la BBDD.
	 * @param id Id del Producto.
	 */
	public void deleteProducto(int id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Producto p = em.find(Producto.class, id);
			em.remove(em.contains(p)? p:em.merge(p));
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}

}
