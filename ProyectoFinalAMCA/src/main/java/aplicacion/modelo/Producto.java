/**
 * 
 */
package aplicacion.modelo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Ángel
 * 
 */

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="stock")
	private int stock = 0;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="caracteristicas")
	private String caracteristicas;
	
	@OneToMany(mappedBy= "producto", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
	private Set<ProductosPedidos> pedidos;
	
//	@ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
//	private Set<Pedido> pedidos;
	
	/**
	 * Constructor por defecto
	 */
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nombre
	 * @param stock
	 * @param precio
	 * Producto con nombre, stock y precio.
	 */
	public Producto(String nombre, int stock, double precio, String caracteristicas) {
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.caracteristicas = caracteristicas;
		pedidos = new HashSet<ProductosPedidos>();
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the pedidos
	 */
	public Set<ProductosPedidos> getPedidos() {
		return pedidos;
	}

	/**
	 * @param pedidos the pedidos to set
	 */
	public void setPedidos (Set<ProductosPedidos> pedidos) {
		this.pedidos = pedidos;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * Imprime por consola los objetos de tipo Producto que se encuentran dentro de un ArrayList<Producto>.
	 * @param ArrayList<Producto> listaProductos.
	 */
	public void imprimirListaProductos(ArrayList<Producto> listaProductos) {
		System.out.println("\nLista Productos:");
		for (Producto p : listaProductos) {
			System.out.println(p.toString());
		}
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto pro = (Producto) o;
        return Objects.equals(nombre, pro.nombre);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}

