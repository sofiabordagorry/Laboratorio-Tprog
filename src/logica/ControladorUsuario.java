package logica;

import excepciones.OfertaLaboralRepetidaException;

import java.util.*;

public class ControladorUsuario implements IUsuario {
	
	public ControladorUsuario() {
	}
	
<<<<<<< HEAD
	public boolean ingresarDatosPostulante(DTPostulante postulante) {
		// pedir instancia manejador usuario
		Usuario user = manejador.buscarUsuario(postulante.getNickname());
		if (user == null) {
			Postulante p = new Postulante(postulante);
			manejador.ingresarPostulante(p);
		}
=======
	/*public void ingresarDatosOL(String empresa, String tipoOL, DTOfertaLaboral datosOL, LinkedList<String> keywords) throws OfertaLaboralRepetidaException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral ol = mol.getOfertaLaboral(datosOL.getNombre());
		if (ol != null)
			throw new OfertaLaboralRepetidaException("La Oferta Laboral %s ya esta registrada".formatted(ol.getNombre()));
		
		ManejadorTipo mt = ManejadorTipo.getInstance();
		Tipo t = mt.getTipo(tipoOL);
		Map<String, Keyword> keys = new HashMap<>();
		for(String k : keywords)
			keys.put(mol.getKeyword(k).getNombre(),mol.getKeyword(k));
		*/
		
		
>>>>>>> 98847bae1d2a1261cde13ef1b51f29df9f4e6eb3
	}
}
