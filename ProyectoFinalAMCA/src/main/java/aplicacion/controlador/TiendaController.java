package aplicacion.controlador;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Producto;
import aplicacion.persistencia.ProductoRepo;

@RequestMapping("/tienda")
@Controller
public class TiendaController {
	
	@Autowired
	private ProductoRepo productoRepo;

	
	@GetMapping(value={"","/"})
	String Productos(Model model) {
		
		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("productos",lista );  
		return "tienda";
	}
	
}
