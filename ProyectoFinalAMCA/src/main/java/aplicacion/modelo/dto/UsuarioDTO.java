package aplicacion.modelo.dto;
// Data Transfer Object

public class UsuarioDTO {
	
	private String username;
	
	private String nombreApellidos;
	
	private String password;
	
	private String email;
	
	private String direccion;


	public String getUsername() {
		return username;
	}

	public void setUsername(String usu) {
		this.username = usu;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioDTO(String usu, String nombreApellidos, String password, String email, String direccion) {
		super();
		this.username = usu;
		this.nombreApellidos = nombreApellidos;
		this.password = password;
		this.email = email;
		this.direccion = direccion;
	}
	
}
