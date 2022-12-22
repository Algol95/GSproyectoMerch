package aplicacion.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
@Table(name="usuario")
public class Usuario implements UserDetails {

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
	
	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
	private Set<Rol> roles;
	
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
		pedidos = new HashSet<Pedido>();
		roles = new HashSet<Rol>();
		
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

	@Override
	/**
	 * @return the usu
	 */
	public String getUsername() {
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
	

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRol())).collect(Collectors.toList());
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
