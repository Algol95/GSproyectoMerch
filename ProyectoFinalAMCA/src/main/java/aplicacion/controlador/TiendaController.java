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

	/**
	 * Metodo que recorre la coleccion de pedidos de un usuario.
	 * 
	 * @param authentication
	 * @return False si la coleccion esta vacia o todos los pedidos estan pagados.
	 *         True si tiene algun pedido sin pagar.
	 */
	private boolean usuarioTienePedidos(Authentication authentication) {
		String username = authentication.getName();
		Usuario u = usuarioRepo.findByUsername(username).get();
		if (u.getPedidos().isEmpty()) {
			return false;
		} else {
			for (Pedido p : pedidoRepo.findAllByUsuario(u)) {
				if (p.isPagado() == false) {
					System.out.println("Hay un pedido sin pagar");
					return true;
				}
			}
			System.out.println("Este mensaje no se debe de mostar");
			return false;
		}

	}

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
			ProductosPedidos productosPedidos = new ProductosPedidos();
			if (usuarioTienePedidos(authentication) == false) {
				double precioTotal = productoRepo.findById(idPro).get().getPrecio() * undCompra.getCantidad();
				pedidoNew = new Pedido(uActualizar.getDireccion(), uActualizar, precioTotal);
				pedidoRepo.save(pedidoNew);
				productosPedidos = new ProductosPedidos(pedidoNew, productoRepo.findById(idPro).get(),
				undCompra.getCantidad());
				productosPedidosRepo.save(productosPedidos);

			} else {
				pedidoNew = pedidoRepo.findByPagado(false).get();
				System.out.println(pedidoNew.toString());
				boolean siNoExistePro = true;
				for (ProductosPedidos proPed : pedidoNew.getProductosPedido()) {
					if (proPed.getProducto().getId()==idPro) {
						System.out.println("nya");
						proPed.setCantidad(proPed.getCantidad() + undCompra.getCantidad());
						pedidoNew.setPrecioTotal(pedidoNew.getPrecioTotal()+ undCompra.getCantidad()*productoRepo.findById(idPro).get().getPrecio());
						productosPedidosRepo.save(proPed);
						pedidoRepo.save(pedidoNew);
						siNoExistePro = false;
						break;
					}
				}
				if (siNoExistePro) {
					System.out.println("La id del pedido es: " +pedidoNew.getId());
					pedidoNew.setPrecioTotal(pedidoNew.getPrecioTotal() + undCompra.getCantidad()*productoRepo.findById(idPro).get().getPrecio());
					productosPedidos = new ProductosPedidos(pedidoNew,productoRepo.findById(idPro).get(), undCompra.getCantidad());
					pedidoNew.getProductosPedido().add(productosPedidos);
					pedidoRepo.save(pedidoNew);
					productosPedidosRepo.save(productosPedidos);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/tienda?error";
		}

		return "redirect:/cesta";
	}

}
