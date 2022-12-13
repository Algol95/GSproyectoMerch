package aplicacion.controlador;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
 

@Controller
public class WebApplication {

	  
	
	@GetMapping("/")
	String home() {
		 
		return "index";
			
	}
	 
	@GetMapping("/bocadillos")
	String homebocatas() {
		 
		return "bocadillos";
			
	}
	
	@GetMapping("/ingredientes")
	String homeingredientes() {
		 
		return "ingredientes";
			
	}

	
	  
}
