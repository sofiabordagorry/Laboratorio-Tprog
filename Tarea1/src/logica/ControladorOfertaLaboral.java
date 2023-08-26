package logica;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
	}
	public DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral) {
		ManejadorOfertaLaboral contOfertaLaboral = ManejadorOfertaLaboral.getInstance();
		DTOfertaLaboral ofLabRes= contOfertaLaboral.buscarOfertaLaboral(OfertaLaboral).getDataOfertaLaboral();
		return ofLabRes;
	}
}
