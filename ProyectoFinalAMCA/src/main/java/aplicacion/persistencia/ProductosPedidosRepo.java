package aplicacion.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.ProductosPedidos;
 
public interface ProductosPedidosRepo extends JpaRepository<ProductosPedidos, Integer> {
	
	
}
