package aplicacion.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**Al crear una tabla ProductosPedidos con sus propios atributos hibernate no lo reconoce por defecto,
 * para que automaticamente reconozca las id y lo aisgne automaticamente es necesaria esta clase, 
 * en los que se especifique la id a encontrar.
 * 
 * @author Ángel
 *
 */

@Embeddable
public class ProductosPedidosId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="pedido_id")                           
	private Integer pedidoId;
	
	@Column(name="producto_id")  
	private Integer productoId;
	
	public ProductosPedidosId(Integer idP, Integer idPr) {
		this.productoId = idPr;
		this.pedidoId = idP;
	}
	
	public ProductosPedidosId() {
		
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || getClass() != o.getClass()) {
			return false;
		}
		ProductosPedidosId that = (ProductosPedidosId) o;
		return Objects.equals(pedidoId, that.pedidoId) && Objects.equals(productoId, that.productoId);
		
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(pedidoId, productoId);
    }
}
