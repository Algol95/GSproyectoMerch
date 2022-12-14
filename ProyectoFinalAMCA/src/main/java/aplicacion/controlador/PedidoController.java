package aplicacion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.*;
import aplicacion.persistencia.PedidoRepo;
import aplicacion.persistencia.ProductoRepo;
import aplicacion.persistencia.UsuarioRepo;

@RequestMapping("/pedidos")
@Controller
public class PedidoController {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private PedidoRepo pedidoRepo;
	@Autowired
	private ProductoRepo productoRepo;

	@GetMapping(value = { "", "/" })
	String alumnos(Model model) {

		model.addAttribute("usuarios", usuarioRepo.findAll());
		model.addAttribute("listaproductos", productoRepo.findAll());
		model.addAttribute("pedidos", pedidoRepo.findAll());

		model.addAttribute("pedidoNuevo", new Pedido());

		return "pedidos";
	}

	@PostMapping("/add")
	public String addPedido(@ModelAttribute("pedidoNuevo") Pedido pedidoNuevo, BindingResult bindingResult) {

		pedidoNuevo.calcularPrecioTotal();

		Usuario usuCliente = usuarioRepo.findById(pedidoNuevo.getUsuario().getId()).get();

		usuCliente.getPedidos().add(pedidoNuevo);
		pedidoNuevo.setUsuario(usuCliente);

		pedidoRepo.save(pedidoNuevo);

		for (Producto pro : pedidoNuevo.getProductos()) {
			pro.getPedidos().add(pedidoNuevo);
			pro.setStock(pro.getStock()-1);
			productoRepo.save(pro);
		}

		return "redirect:/pedidos";
	}

	@GetMapping({ "/{id}" })
	public String obtenerPedido(Model model, @PathVariable Integer id) {

		model.addAttribute("usuario", usuarioRepo.findById(id).get());

		return "pedido";
	}

	@GetMapping({ "/buscar/{nombre}" })
	public String obtenerPedido(@PathVariable String nombre) {
		return "pedido";
	}

	@GetMapping({ "/delete/{id}" })
	public String borrarPedido(@PathVariable Integer id) {

		return "redirect:/pedidos";

	}

}
