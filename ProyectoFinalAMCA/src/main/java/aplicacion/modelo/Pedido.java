package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="entregado")
	private boolean entregado;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="precio_total")
	private double precioTotal;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "pedidos_productos",
			joinColumns = {@JoinColumn(name = "id_pedido")},
			inverseJoinColumns = {@JoinColumn(name = "id_producto")}
	)	
	private Set<Producto> productos;
	
	@ManyToOne
	@JoinColumn(name = "id_usu", nullable = false)
	private Usuario usuario;

	/**
	 * @param id
	 * @param entregado
	 * @param direccion
	 * @param unidades
	 * @param precioTotal
	 * @param productos
	 * @param usuario
	 */
	public Pedido(boolean entregado, String direccion, Usuario usuario) {
		this.entregado = entregado;
		this.direccion = direccion;
		productos = new HashSet<Producto>();
		precioTotal = 0;
		for (Producto p : productos) {
			precioTotal += p.getPrecio();
		}
		this.usuario = usuario;
	}
	
	public Pedido() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the entregado
	 */
	public boolean isEntregado() {
		return entregado;
	}

	/**
	 * @param entregado the entregado to set
	 */
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * @return the productos
	 */
	public Set<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
