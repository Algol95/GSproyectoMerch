package aplicacion.controlador;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplicacion.modelo.Producto;
import aplicacion.persistencia.ProductoRepo;



@RequestMapping("/productos")
@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepo productoRepo;


	@GetMapping(value={"","/"})
	String Productos(Model model) {
		
		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("productos",lista );
		model.addAttribute("nuevoProducto",new Producto() );
		model.addAttribute("productoaEditar", new Producto());    
		model.addAttribute("nombreNuevo", "");    
		return "productos";
	}

//	@GetMapping(value="/add/{nombre}")
//	public String insertarProducto(Model model, @PathVariable String pro ) {
//		 
//		System.out.println("Insertando Producto nuevo: "+pro);		
//		
//		return "redirect:/productos";
//	}
	
	
	@PostMapping("/add")
	public String addProducto(@ModelAttribute("nuevoProducto") Producto producto, BindingResult bindingResult) {
		productoRepo.save(producto);
		System.out.println("Insertando Usuario nuevo: "+ producto.getNombre());	
				
		return "redirect:/productos";	
	}
	 
	@PostMapping("/edit/{id}")
	public String editarProducto(@PathVariable Integer id, @ModelAttribute("productoaEditar") Producto producto,BindingResult bindingResult) {
		
		Producto proActualizar = productoRepo.findById(id).get();
		if(producto.getNombre() != "") {
			proActualizar.setNombre(producto.getNombre());
		}
		if(producto.getPrecio() != 0) {
			proActualizar.setPrecio(producto.getPrecio());
		}
		if(producto.getStock() != 0) {
			proActualizar.setStock(producto.getStock());
		}
		if(producto.getCaracteristicas() != "") {
			proActualizar.setCaracteristicas(producto.getCaracteristicas());
		}
		
		productoRepo.save(proActualizar);
		
		return "redirect:/productos";
		 
	} 
	
	@GetMapping({"/{id}"})
	public String obtenerProduct(Model model, @PathVariable Integer id) {
	 
		model.addAttribute("producto", productoRepo.findById(id).get());
		
		return "producto";
	}
	
	@GetMapping({"/buscar/{nombre}"})
	public String obtenerProducto(@PathVariable String nombre) {
		return "producto";
	}
	
	@GetMapping({"/delete/{id}"})
	public String borrarProducto(@PathVariable Integer id) {
		
		productoRepo.deleteById(id);
		
		return "redirect:/productos";
		 
	}
	
}
