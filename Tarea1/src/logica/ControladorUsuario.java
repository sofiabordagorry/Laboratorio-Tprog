package logica;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
	public boolean ingresarDatosPostulante(DTPostulante post) {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(post.getNickname());
		if (user == null) {
			Postulante new_user = new Postulante(post.getNickname(), post.getNombre(), post.getApellido(), post.getCorreo(), post.getFechaDeNacimiento(), post.getNacionalidad());
			m.agregarPostulante(new_user);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean ingresarDatosEmpresa(DTEmpresa emp) {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(emp.getNickname());
		if (user == null) {
			Empresa new_user = new Empresa(emp.getNickname(), emp.getNombre(), emp.getApellido(), emp.getCorreo(), emp.getNombreDeEmpresa(), emp.getDescripcion(), emp.getLink());
			m.agregarEmpresa(new_user);
			return true;
		} else {
			return false;
		}
	}
}
