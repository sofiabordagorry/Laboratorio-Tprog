package logica;

import excepciones.OfertaLaboralRepetidaException;

import java.util.*;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
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
		
		
	}
}
