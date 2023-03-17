package aplicacion.servicio.interfaces;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import aplicacion.modelo.Usuario;
import aplicacion.modelo.dto.UsuarioDTO;

public interface UsuarioService extends UserDetailsService{

	@Bean
	public Usuario save(UsuarioDTO usuarioRegistroDTO);
	
	
	
}
