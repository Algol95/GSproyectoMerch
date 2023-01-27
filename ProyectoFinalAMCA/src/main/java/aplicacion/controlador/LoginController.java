package aplicacion.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {
	
	@GetMapping(value={"","/"})
	String homelogin(Model model) {
		return "login";
	}
	
}
