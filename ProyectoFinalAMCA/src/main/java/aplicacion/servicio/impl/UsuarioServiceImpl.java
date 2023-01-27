package aplicacion.servicio.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import aplicacion.modelo.Rol;
import aplicacion.modelo.Usuario;
import aplicacion.modelo.dto.UsuarioDTO;
import aplicacion.persistencia.RolRepo;
import aplicacion.persistencia.UsuarioRepo;
import aplicacion.servicio.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepo usuarioRepo;
	private RolRepo rolRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepo usuarioRepo, RolRepo rolRepo) {
		super();
		this.usuarioRepo = usuarioRepo;
		this.rolRepo = rolRepo;
	}

	@Override
	public Usuario save(UsuarioDTO registroDTO) {
		Usuario usu = new Usuario(registroDTO.getUsername(), registroDTO.getNombreApellidos(), passwordEncoder.encode(registroDTO.getPassword()),
				registroDTO.getEmail(), registroDTO.getDireccion(), Arrays.asList(new Rol("ROLE_USER")));
		Rol rol = rolRepo.findById(1).get();
		rol.getUsuarios().add(usu);
		usuarioRepo.save(usu);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = usuarioRepo.findByUsername(username);
		if(usuario.isPresent()) {
			Usuario loadedUsuario = usuario.get();
			return loadedUsuario;
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
	}

}
