//package aplicacion.controlador;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import aplicacion.modelo.Alumno;
//import aplicacion.modelo.Bocadillo;
//import aplicacion.modelo.Pedido;
//import aplicacion.persistencia.AlumnoDAO;
//import aplicacion.persistencia.BocadilloDAO;
//import aplicacion.persistencia.PedidoDAO;
//
//@RequestMapping("/pedidos")
//@Controller
//public class PedidoController {
//
//	// private AlumnoServiceImp alumnoService = new AlumnoServiceImp();
//
//	private BocadilloDAO crudBocadillo = new BocadilloDAO();
//	private PedidoDAO crudPedido = new PedidoDAO();
//	private AlumnoDAO crudAlumno = new AlumnoDAO();
//
//	@GetMapping(value = { "", "/" })
//	String alumnos(Model model) {
//
//		model.addAttribute("alumnos", crudAlumno.listarAlumnosJPA());
//		model.addAttribute("listabocadillos", crudBocadillo.listarBocadilloJPA());
//		model.addAttribute("pedidos", crudPedido.listarPedidoJPA());
//
//		model.addAttribute("pedidoNuevo", new Pedido());
//
//		return "pedidos";
//	}
//
//	@PostMapping("/add")
//	public String addPedido(@ModelAttribute("pedidoNuevo") Pedido pedidoNuevo, BindingResult bindingResult) {
//
//		pedidoNuevo.calcularPrecio();
//
//		Alumno alumnoNuevo = crudAlumno.buscarPorIdJPA(pedidoNuevo.getAlumno().getId());
//
//		alumnoNuevo.getPedidos().add(pedidoNuevo);
//		pedidoNuevo.setAlumno(alumnoNuevo);
//
//		crudPedido.insertarPedidoJPA(pedidoNuevo);
//
//		for (Bocadillo b : pedidoNuevo.getBocadillos()) {
//			b.getPedidos().add(pedidoNuevo);
//			crudBocadillo.modificarBocadilloJPA(b);
//		}
//
//		return "redirect:/pedidos";
//	}
//
//	@GetMapping({ "/{id}" })
//	public String obtenerPedido(Model model, @PathVariable Integer id) {
//
//		model.addAttribute("alumno", crudAlumno.buscarPorIdJPA(id));
//
//		return "pedido";
//	}
//
//	@GetMapping({ "/buscar/{nombre}" })
//	public String obtenerPedido(@PathVariable String nombre) {
//		return "pedido";
//	}
//
//	@GetMapping({ "/delete/{id}" })
//	public String borrarPedido(@PathVariable Integer id) {
//
//		return "redirect:/pedidos";
//
//	}
//
//}
