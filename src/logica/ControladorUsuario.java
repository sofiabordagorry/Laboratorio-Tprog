package logica;

public class ControladorUsuario implements IUsuario {
	
	public ControladorUsuario() {
	}
	
	public boolean ingresarDatosPostulante(DTPostulante postulante) {
		// pedir instancia manejador usuario
		Usuario user = manejador.buscarUsuario(postulante.getNickname());
		if (user == null) {
			Postulante p = new Postulante(postulante);
			manejador.ingresarPostulante(p);
		}
	}
}
