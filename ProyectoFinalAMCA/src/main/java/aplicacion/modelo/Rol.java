package aplicacion.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(name="roles")
	public String rol;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_usuarios",
			joinColumns = {@JoinColumn(name = "id_rol")},
			inverseJoinColumns = {@JoinColumn(name = "id_usuario")}
	)	
	private Set<Usuario> usuarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
