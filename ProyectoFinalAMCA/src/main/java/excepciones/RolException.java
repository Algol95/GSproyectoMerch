package excepciones;

public class RolException extends Exception {

	public RolException(String msg) {
		super(msg);
	}
	
	public RolException() {

	}

	public void errRol(int rol) throws RolException{
		if(rol<0||rol>1) {
			throw new RolException("Los roles contemplados unicamente son 0(Admin) o 1(Estandar),\n automaticamente se le adjudica rol de usuario estandar");
		}
	}
}
