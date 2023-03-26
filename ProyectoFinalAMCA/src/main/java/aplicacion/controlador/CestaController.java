package aplicacion.controlador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aplicacion.modelo.Pedido;
import aplicacion.modelo.Producto;
import aplicacion.modelo.ProductosPedidos;
import aplicacion.modelo.Usuario;
import aplicacion.persistencia.PedidoRepo;
import aplicacion.persistencia.ProductoRepo;
import aplicacion.persistencia.ProductosPedidosRepo;
import aplicacion.persistencia.UsuarioRepo;

@RequestMapping("/cesta")
@Controller
public class CestaController {

	@Autowired
	private ProductoRepo productoRepo;
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private PedidoRepo pedidoRepo;
	@Autowired
	private ProductosPedidosRepo productosPedidosRepo;

	@GetMapping(value = { "", "/" })
	String Productos(Model model, Authentication authentication) {
		String userAuth = authentication.getName();
		Pedido pedido = new Pedido();
		Set<ProductosPedidos> cesta = new HashSet<ProductosPedidos>();
		for (Pedido p : pedidoRepo.findAllByUsuario(usuarioRepo.findByUsername(userAuth).get())) {
			if (p.isPagado() == false) {
				cesta = p.getProductosPedido();
				pedido = p;
				break;
			}
		}
		model.addAttribute("cesta", cesta);
		model.addAttribute("pedido", pedido);
		model.addAttribute("undProPed", new ProductosPedidos());
		model.addAttribute("pagoDeshabilitado", cesta.isEmpty());
		return "cesta";
	}

	@PostMapping("/addProducto/{idPed}/{idPro}")
	public String addProducto(@PathVariable Integer idPed, @PathVariable Integer idPro,
			@ModelAttribute("undProPed") ProductosPedidos undCompra, BindingResult bindingResult) {
		try {
			Pedido pedidoAct = pedidoRepo.findById(idPed).get();
			Producto pro = productoRepo.findById(idPro).get();
			int cantidadCompra = undCompra.getCantidad();
			for (ProductosPedidos productosPedidosAct : pedidoAct.getProductosPedido()) {
				if (productosPedidosAct.getProducto().getId() == idPro) {
					int cantidadAnterior = productosPedidosAct.getCantidad();
					double precioProductosAnterior = cantidadAnterior * pro.getPrecio();
					productosPedidosAct.setCantidad(cantidadCompra);
					pedidoAct.setPrecioTotal(
							pedidoAct.getPrecioTotal() - precioProductosAnterior + cantidadCompra * pro.getPrecio());
					productosPedidosAct.setPedido(pedidoAct);
					productosPedidosRepo.save(productosPedidosAct);
					pedidoRepo.save(pedidoAct);
				}

			}
			return "redirect:/cesta";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/cesta";
		}
	}

	@GetMapping("/delete/{idPed}/{idPro}")
	public String deleteProducto(@PathVariable Integer idPed, @PathVariable Integer idPro) {
		Pedido pedidoDel = pedidoRepo.findById(idPed).get();
		Producto pro = productoRepo.findById(idPro).get();
		for (ProductosPedidos productosPedidosDel : pedidoDel.getProductosPedido()) {
			if(productosPedidosDel.getProducto().getId()==idPro) {
				int cantidadAnterior = productosPedidosDel.getCantidad();
				double precioProductosAnterior = cantidadAnterior * pro.getPrecio();
				pro.getPedidos().remove(productosPedidosDel);
				productoRepo.save(pro);
				pedidoDel.getProductosPedido().remove(productosPedidosDel);
				pedidoRepo.save(pedidoDel);
				productosPedidosRepo.delete(productosPedidosDel);
				if(pedidoDel.getProductosPedido().isEmpty()) {
					pedidoRepo.delete(pedidoDel);
				} else {
					pedidoDel.setPrecioTotal(pedidoDel.getPrecioTotal()-precioProductosAnterior);
					pedidoRepo.save(pedidoDel);
				}
			}
		}
		return "redirect:/cesta";
	}
	
	@GetMapping("/pagar/{idPed}")
	public String pagarPedido(@PathVariable Integer idPed) {
		Pedido pedidoPagado = pedidoRepo.findById(idPed).get();

		pedidoPagado.setPagado(true);
		pedidoRepo.save(pedidoPagado);
		return "redirect:/cesta";
	}

}
