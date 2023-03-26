package aplicacion.controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Pedido;
import aplicacion.modelo.ProductosPedidos;
import aplicacion.persistencia.PedidoRepo;
import aplicacion.persistencia.ProductoRepo;
import aplicacion.persistencia.UsuarioRepo;

@RequestMapping("/mispedidos")
@Controller
public class MisPedidosController {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private PedidoRepo pedidoRepo;
	@Autowired
	private ProductoRepo productoRepo;

	@GetMapping(value = { "", "/" })
	String Productos(Model model, Authentication authentication) {
		String userAuth = authentication.getName();
		List <Pedido> pedidos = new ArrayList<Pedido>();
		int count = 0;
		for (Pedido p : pedidoRepo.findAllByUsuario(usuarioRepo.findByUsername(userAuth).get())){
			if (p.isPagado()) {
				pedidos.add(count, p);
				count++;
			}	
		}
		System.out.println(pedidos.size());
		model.addAttribute("pedidos", pedidos);
		return "mispedidos";
	}
	
}
