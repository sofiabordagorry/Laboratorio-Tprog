package logica;

public class Factory {
	
	private static Factory instance;
	
	private Factory() {
	};
	
	public static Factory getInstance() {
		if (instance == null) {
			instance = new Factory();
		}
		
		return instance;
	}
	
	public IUsuario getIUsuario( ) {
		return new ControladorUsuario();
	}
	
	public IOfertaLaboral getIOfertaLaboral() {
		return new ControladorOfertaLaboral();
	}
}
