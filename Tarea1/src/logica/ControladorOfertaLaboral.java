package logica;


import excepciones.NoExistenPaquetesException;
import excepciones.KeywordsNoExistenException;
import excepciones.TipoPubNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteRepetidoException;

import java.util.Map;
import java.util.HashMap;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
	}

	public DTPaquete[] listarPaquetes() {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Paquete[] paquetes = mt.getPaquetes();
		
		if (paquetes != null) {
			DTPaquete[] dt = new DTPaquete[paquetes.length];
			Paquete paq;
			
			for(int i = 0; i <paquetes.length; i++) {
				paq = paquetes[i];
				dt[i] = paq.getDataPaquete();
			}
			
			return dt;
		} else {
			return null;
		}
	}
	
    public String DatosPaqueteAMostrar(DTPaquete p) {
    	return "Nombre: " + p.getNombre() + "\nDescripcion: " + p.getDescripcion() + "\nPeriodo de validez: " + p.getPeriodoDeValidez() + "\nDescuento: " + p.getDescuento() + "\nCosto: " + p.getCostoAsociado();
    }

	public DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Tipo[] tipos = mt.getTipos();
		
		if (tipos != null) {
			DTTipo[] dt = new DTTipo[tipos.length];
			Tipo tipo;
			
			for(int i = 0; i <tipos.length; i++) {
				tipo = tipos[i];
				dt[i] = tipo.getDataTipo();
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
	
	public void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Paquete p = mt.buscarPaquete(datosPaquete.getNombre());
		
		if(p != null)
			throw new PaqueteRepetidoException("Ya existe el Paquete %s".formatted(datosPaquete.getNombre()));
		
		Paquete nuevoPaquete = new Paquete(datosPaquete.getNombre(), datosPaquete.getDescripcion(), datosPaquete.getPeriodoDeValidez(), 
														datosPaquete.getDescuento(), datosPaquete.getCostoAsociado());
		
		mt.agregarPaquete(nuevoPaquete);
	}
}
