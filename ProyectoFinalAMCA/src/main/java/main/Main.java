/**
 * 
 */
package main;

import models.*;
import mDAO.*;

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
		
		Usuario usu = new Usuario("paco777", "Paco Martin", "paco1234", "paco777@gmail.com", "Calle Quinto Pino n7");
		UsuarioDAO uDAO = new UsuarioDAO();
		//---- INSERCION DE DATOS ----
		
//		pDAO.createProducto(p1);
//		pDAO.createProducto(p2);
//		pDAO.createProducto(p3);
		uDAO.createUsuario(usu);
		
		//---- LECTURA DE DATOS ----
		
		pDAO.imprimirListaProductos(pDAO.readProducto());
		
		//---- ACTUALIZACION DE DATOS ----
		
//		pDAO.updateProductoPrecio(2, 9.99);
//		pDAO.updateProducto(2, p4);

	}

}
