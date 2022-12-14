package aplicacion.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Pedido;
 
public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
	
	
}
