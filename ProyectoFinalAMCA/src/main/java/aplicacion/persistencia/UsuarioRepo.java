package aplicacion.persistencia;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Usuario;
 
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findByUsername(String user);
}
