/**
 * 
 */
package principal.main;

import principal.models.*;
import principal.persistencia.*;

/**
 * @author Ángel
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Creacion de objetos
		Producto p1 = new Producto("Pegatina", 100, 2.99);
		Producto p2 = new Producto("Poster", 100, 8);
		Producto p3 = new Producto("Camiseta", 50, 15.7);
		Producto p4 = new Producto("Llavero", 200, 7.5);
		ProductoDAO pDAO = new ProductoDAO();
		Usuario u1 = new Usuario("paco777", "Paco Martin", "paco1234", "paco777@gmail.com", "Calle Quinto Pino n7");
		UsuarioDAO uDAO = new UsuarioDAO();
		Pedido ped1 = new Pedido(false, u1.getDireccion(), u1);
		PedidoDAO pedDAO = new PedidoDAO();
		
		// ---- OBJETOS VACIOS ----
		Producto pCtr = new Producto();
		Usuario uCtr = new Usuario();
		
		// --- Se establecen Datos
		p1.getPedidos().add(ped1);
		p2.getPedidos().add(ped1);
		u1.getPedidos().add(ped1);
		ped1.getProductos().add(p1);
		ped1.getProductos().add(p2);
		
		//---- INSERCION DE DATOS ----
		
		pDAO.createProducto(p1);
		pDAO.createProducto(p2);
		pDAO.createProducto(p3);
		uDAO.createUsuario(u1);
		pedDAO.createPedido(ped1);
		
		//---- LECTURA DE DATOS ----
		
//		pCtr.imprimirListaProductos(pDAO.readProducto());
		
		//---- ACTUALIZACION DE DATOS ----
		
//		pDAO.updateProductoPrecio(2, 9.99);
//		pDAO.updateProducto(2, p4);

	}

}
