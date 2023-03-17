package aplicacion.controlador;

import java.util.List;

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

@RequestMapping("/tienda")
@Controller
public class TiendaController {

	@Autowired
	private ProductoRepo productoRepo;
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private PedidoRepo pedidoRepo;
	@Autowired
	private ProductosPedidosRepo productosPedidosRepo;

	@GetMapping(value = { "", "/" })
	String Productos(Model model) {

		List<Producto> lista = productoRepo.findAll();
		model.addAttribute("productos", lista);
		model.addAttribute("nuevoPedido", new Pedido());
		model.addAttribute("undProPed", new ProductosPedidos());
		return "tienda";
	}

//	private boolean usuarioTienePedidos(Authentication authentication) {
//		String username = authentication.getName();
//		System.out.println(username);
//		Usuario u = usuarioRepo.findByUsername(username).get();
//		if (u.getPedidos().isEmpty()) {
//			return false;
//		} else {
//			for (Pedido p : u.getPedidos()) {
//				if (p.isPagado()) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	@GetMapping(value = "/addPedido/{id}")
	public String insertarPedido(Model model) {
		return "redirect:/tienda";
	}

	@PostMapping("/addPedido/{idPro}")
	public String addProducto(@PathVariable Integer idPro, @ModelAttribute("undProPed") ProductosPedidos undCompra,
			Authentication authentication, BindingResult bindingResult) {
		try {
			Usuario uActualizar = usuarioRepo.findByUsername(authentication.getName()).get();
			Pedido pedidoNew = new Pedido();
			ProductosPedidos productosPedidos = new ProductosPedidos() ;
//			if (usuarioTienePedidos(authentication)==false) {
			double precioTotal = productoRepo.findById(idPro).get().getPrecio() * undCompra.getCantidad();
			pedidoNew = new Pedido(uActualizar.getDireccion(), uActualizar, precioTotal);
			pedidoRepo.save(pedidoNew);
					productosPedidos = new ProductosPedidos(pedidoNew, productoRepo.findById(idPro).get(),
					undCompra.getCantidad());
					productosPedidosRepo.save(productosPedidos);
		} catch (Exception e) {
			return "redirect:/tienda?error";
		}

//		} else {
//			pedidoNew = pedidoRepo.findByPagado(false).get();
//			productosPedidos = new ProductosPedidos(pedidoNew, productoRepo.findById(idPro).get(),undCompra.getCantidad());
//			boolean controlProductoExiste=false;
//			for (ProductosPedidos p : pedidoNew.getProductosPedido()) {
//				if (p.getId()==productosPedidos.getId()) {
//					p.setCantidad(p.getCantidad() + undCompra.getCantidad());
//					double precioASumar = productoRepo.findById(idPro).get().getPrecio() * undCompra.getCantidad();
//					pedidoNew.setPrecioTotal(pedidoNew.getPrecioTotal()+precioASumar);
//					pedidoRepo.save(pedidoNew);
//					productosPedidosRepo.save(p);
//					controlProductoExiste = true;
//					break;
//				}
//			}
//			if (controlProductoExiste==false) {
//				
//			}
//		}
		return "redirect:/tienda?exito";
	}

}
