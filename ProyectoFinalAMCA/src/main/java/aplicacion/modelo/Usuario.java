package aplicacion.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nick",unique=true)
	private String usu;
	
	@Column(name="nombre_apellidos")
	private String nombreApellidos;
	
	@Column(name="password")
	private String password;
	
	@Column(name="correo_electronico", unique=true)
	private String email;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="rol")
	private int rol;
	
	@OneToMany(mappedBy= "usuario",  fetch = FetchType.EAGER)
	private Set<Pedido> pedidos;

	/**
	 * Constructor para usuarios estandar.
	 * @param usu
	 * @param nombreApellidos
	 * @param password
	 * @param email
	 * @param direccion
	 */
	public Usuario(String usu, String nombreApellidos, String password, String email, String direccion) {
		this.usu = usu;
		this.nombreApellidos = nombreApellidos;
		this.password = password;
		this.email = email;
		this.direccion = direccion;
		rol = 1;
		pedidos = new HashSet<Pedido>();
	}
	
	/**
	 * Constructor para usuarios, cualquier tipo.
	 * @param usu
	 * @param nombreApellidos
	 * @param password
	 * @param email
	 * @param direccion
	 * @param rol 0=Admin 1=Estandar
	 */
	public Usuario(String usu, String nombreApellidos, String password, String email, String direccion, int rol) {
		this.usu = usu;
		this.nombreApellidos = nombreApellidos;
		this.password = password;
		this.email = email;
		this.direccion = direccion;
		this.rol= rol;
		pedidos = new HashSet<Pedido>();
	}
	
	public Usuario() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the usu
	 */
	public String getUsu() {
		return usu;
	}

	/**
	 * @param usu the usu to set
	 */
	public void setUsu(String usu) {
		this.usu = usu;
	}

	/**
	 * @return the nombreApellidos
	 */
	public String getNombreApellidos() {
		return nombreApellidos;
	}

	/**
	 * @param nombreApellidos the nombreApellidos to set
	 */
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the rol
	 */
	public int getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return the pedidos
	 */
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	/**
	 * @param pedidos the pedidos to set
	 */
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void imprimirListaUsuario(ArrayList<Usuario> listaUsuarios) {
		System.out.println("\nLista Usuarios:");
		for (Usuario u : listaUsuarios) {
			System.out.println(u.toString());
		}
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usu=" + usu + ", nombreApellidos=" + nombreApellidos + ", password=" + password
				+ ", email=" + email + ", direccion=" + direccion + "]";
	}
	
	
}
