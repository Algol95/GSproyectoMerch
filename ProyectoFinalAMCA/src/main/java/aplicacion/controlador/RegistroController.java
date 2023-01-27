package aplicacion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.dto.UsuarioDTO;
import aplicacion.servicio.interfaces.UsuarioService;

@RequestMapping("/registro")
@Controller
public class RegistroController {

	@Autowired
	private UsuarioService usuarioService;
	
	public RegistroController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioDTO nuevoRegistroUsuario() {
		return new UsuarioDTO();
	}
	
	@GetMapping
	public String formularoRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioReg) {
		usuarioService.save(usuarioReg);
		return "redirect:/registro?exito";
	}
	
}
