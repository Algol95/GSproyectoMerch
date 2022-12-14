package aplicacion.persistencia;


import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Producto;

 
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
	
	
}
