package aplicacion.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity(name="ProductosPedidos")
@Table(name="productos_pedidos")
public class ProductosPedidos {
	
	@EmbeddedId
	private ProductosPedidosId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("pedido_id")
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@MapsId("producto_id")
	private Producto producto;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name = "fecha")
	private String fecha;
	
	String nombre;
	
	public ProductosPedidos() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductosPedidos(Pedido p, Producto pr, Integer c) {
		this.producto=pr;
		this.pedido=p;
		this.cantidad=c;
		this.nombre= pr.getNombre();
		this.id = new ProductosPedidosId(p.getId(), pr.getId());
		LocalDate fechaActual = LocalDate.now();
	    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    fecha = fechaActual.format(formateador);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || getClass()!=o.getClass()) {
			return false;
		}
		ProductosPedidos that =(ProductosPedidos) o;
		return Objects.equals(producto, that.producto) && Objects.equals(pedido, that.pedido);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pedido, producto);
	}

	public ProductosPedidosId getId() {
		return id;
	}

	public void setId(ProductosPedidosId id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}