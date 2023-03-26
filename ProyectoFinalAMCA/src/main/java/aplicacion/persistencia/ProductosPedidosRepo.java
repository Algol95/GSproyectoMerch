package aplicacion.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.ProductosPedidos;
 
public interface ProductosPedidosRepo extends JpaRepository<ProductosPedidos, Integer> {
	
	 public Optional<ProductosPedidos> findById(String Id);
	 
}
