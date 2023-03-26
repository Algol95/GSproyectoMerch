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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;

import aplicacion.modelo.*;
import aplicacion.persistencia.PedidoRepo;
import aplicacion.persistencia.ProductoRepo;
import aplicacion.persistencia.ProductosPedidosRepo;
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
	@Autowired
	private ProductosPedidosRepo productosPedidosRepo;

	@GetMapping(value = { "", "/" })
	String pedidos(Model model) {

		model.addAttribute("pedidos", pedidoRepo.findAll());
		model.addAttribute("pedidoaEditar", new Pedido());

		return "pedidos";
	}
	
	@PostMapping("/edit/{id}")
	public String editarPedido(@PathVariable Integer id, @ModelAttribute("pedidoaEditar") Pedido pedido, BindingResult bindingResult) {
		
		Pedido pedActualizar = pedidoRepo.findById(id).get();
		if(pedido.getDireccion() != "") {
			pedActualizar.setDireccion(pedido.getDireccion());
		}
		
		if(pedido.getPrecioTotal() != null) {
			pedActualizar.setPrecioTotal(pedido.getPrecioTotal());
		}
		if(pedido.isEntregado() != pedActualizar.isEntregado()) {
			pedActualizar.setEntregado(pedido.isEntregado());
		}
		if(pedido.isPagado() != pedActualizar.isPagado()) {
			pedActualizar.setPagado(pedido.isPagado());
		}
		
		pedidoRepo.save(pedActualizar);
		
		return "redirect:/pedidos";
		 
	} 
	
	@GetMapping("/delete/{id}")
	public String deletePedido(@PathVariable Integer id) {
		Pedido pedidoDel = pedidoRepo.findById(id).get();
		for (ProductosPedidos proPed : pedidoDel.getProductos() ) {
			productosPedidosRepo.delete(proPed);
		}
		pedidoRepo.delete(pedidoDel);
		return "redirect:/pedidos";
	}

}
