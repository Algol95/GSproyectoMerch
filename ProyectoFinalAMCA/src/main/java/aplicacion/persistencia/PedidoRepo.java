package aplicacion.persistencia;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Pedido;
import aplicacion.modelo.Usuario;
 
public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
	
	public Optional<Pedido> findByPagado(Boolean pagado);
	public List<Pedido> findAllByUsuario(Usuario u);
	public Set<Pedido> findAllByPagado(Boolean pagado);
}
