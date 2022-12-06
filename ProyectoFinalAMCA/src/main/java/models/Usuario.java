package models;

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
	}
	
	public Usuario() {
		
	}
	
	

}
