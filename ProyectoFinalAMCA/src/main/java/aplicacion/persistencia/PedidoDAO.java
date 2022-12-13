package aplicacion.persistencia;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import aplicacion.modelo.Pedido;
import aplicacion.modelo.Producto;
import aplicacion.modelo.Usuario;
import aplicacion.utils.JPAUtil;

public class PedidoDAO {
	
	ProductoDAO productoDAO = new ProductoDAO();


	/**
	 * Crea un objeto de tipo Pedido en la BBDD y resta el Stock del producto.
	 * @param p Objeto de tipo Pedido.
	 * @param idProducto ID del producto.
	 */
	public void createPedido(Pedido p) {
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
	 * Devuelve una coleccion de objetos de tipo Pedido al leer la tabla Pedido.
	 * 
	 * @return ArrayList<Pedido> listaPedidos.
	 */
	public ArrayList<Pedido> readPedido() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Pedido> listaPedidos = (ArrayList<Pedido>) em.createQuery("from Pedido").getResultList();
			em.getTransaction().commit();
			return listaPedidos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		return null;
	}

	/**
	 * Metodo que obtiene un Pedido segun su id y actualiza el precio Total segun el pasado por parametro.
	 * @param id ID del Pedido.
	 * @param precioUnidad Precio de la unidad.
	 */
	public void updatePedidoPrecioTotal(int id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Pedido p = em.find(Pedido.class, id);
			double precio = 0;
			for (Producto pro : p.getProductos()) {
				precio += pro.getPrecio();
			}
			p.setPrecioTotal(precio);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que obtiene un Pedido segun su id y actualiza la direccion segun la pasada por parametro.
	 * @param id Id del Pedido.
	 * @param stock Nuevo stock del Pedido.
	 */
	public void updatePedidoDireccion(int id, Usuario usu) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Pedido p = em.find(Pedido.class, id);
			p.setDireccion(usu.getDireccion());
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo que obtiene un Pedido segun su id y actualiza el estado de entregado
	 * @param id
	 * @param entregado
	 */
	public void updatePedidoEntregado(int id, boolean entregado) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try { 
			Pedido p = em.find(Pedido.class, id);
			p.setEntregado(entregado);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
	}
	
	/**
	 * Metodo para borrar un objeto de tipo Pedido de la BBDD.
	 * @param id Id del Pedido.
	 */
	public void deletePedido(int id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Pedido p = em.find(Pedido.class, id);
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
