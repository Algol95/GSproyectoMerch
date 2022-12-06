/**
 * 
 */
package models;
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
	private int stock;
	
	@Column(name="precio")
	private double precio;
	
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
	public Producto(String nombre, int stock, double precio) {
		super();
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
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

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + "]";
	}
	
	

}
