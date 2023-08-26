package logica;

import excepciones.KeywordsNoExistenException;
import excepciones.TipoPubNoExistenException;
import excepciones.OfertaLaboralRepetidaException;

import java.util.Map;
import java.util.HashMap;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
	}
	
	public DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Tipo[] tipos = mt.getTipos();
		
		if (tipos != null) {
			DTTipo[] dt = new DTTipo[tipos.length];
			Tipo tipo;
			
			for(int i = 0; i <tipos.length; i++) {
				tipo = tipos[i];
				dt[i] = new DTTipo(tipo.getNombre(), tipo.getDescripcion(), tipo.getExposicion(), 
										tipo.getDuracion(), tipo.getCosto(), tipo.getFechaDeAlta());
			}
			
			return dt;
		} else 
			throw new TipoPubNoExistenException("No existen Tipos de Publicaciones de Ofertas Laborales registradas");
	}
	
	public String[] listarKeywords() throws KeywordsNoExistenException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		
		if(keys != null) {
			String[] k = new String[keys.length];
			Keyword key;
			
			for(int i = 0; i < keys.length; i++) {
				key = keys[i];
				k[i] = key.getNombre();
			}
			
			return k;
		} else 
			throw new KeywordsNoExistenException("No existen Keywords registradas");
	}
	
	public void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ol) throws OfertaLaboralRepetidaException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral olExiste = mol.buscarOfertaLaboral(ol.getNombre());
		
		if(olExiste != null) 
			throw new OfertaLaboralRepetidaException("Ya existe la Oferta Laboral %s".formatted(olExiste.getNombre()));
		
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Tipo t = mt.buscarTipo(nombreTipo);
		
		Map<String, Keyword> keys = new HashMap<>();	
		for(Map.Entry<String, DTKeyword> entry: ol.getKeywords().entrySet()) 
			keys.put(entry.getKey(), mol.buscarKeyword(entry.getKey()));
		
		OfertaLaboral olNueva = new OfertaLaboral(ol.getNombre(), ol.getDescripcion(), ol.getCiudad(), ol.getDepartamente(), 
															ol.getHorario(), ol.getRemuneracion(), ol.getFechaDeAlta(), ol.getCostoAsociado(), t, keys);
		
		mol.agregarOfertaLaboral(olNueva);
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Empresa e = mu.buscarEmpresa(empresa);
		e.agregarOfertaLaboral(olNueva);
	}
}
