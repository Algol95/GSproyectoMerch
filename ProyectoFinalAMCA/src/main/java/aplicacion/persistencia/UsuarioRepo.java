package aplicacion.persistencia;


import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Usuario;
 
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	
}
