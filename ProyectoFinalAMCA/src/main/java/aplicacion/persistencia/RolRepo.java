package aplicacion.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Rol;

public interface RolRepo extends JpaRepository<Rol, Integer> {

}
