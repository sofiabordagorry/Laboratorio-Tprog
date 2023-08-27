package logica;


import excepciones.NoExistenPaquetesException;
import excepciones.KeywordsNoExistenException;
import excepciones.TipoPubNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteRepetidoException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.TipoRepetidoException;
import excepciones.TipoYaAgragadoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
		
	}
	
	public DTPaquete[] listarPaquetes() throws NoHayPaquetesException {
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
			throw new NoHayPaquetesException("No hay paquetes registrados");
		}
	}
	
    public String DatosPaqueteAMostrar(DTPaquete p) {
    	return "Nombre: " + p.getNombre() + "\nDescripcion: " + p.getDescripcion() + "\nPeriodo de validez: " + p.getPeriodoDeValidez() + " días\nDescuento: " + p.getDescuento() + "%\nCosto: $" + p.getCostoAsociado();
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
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Empresa e = mu.buscarEmpresa(empresa);
		if(olExiste != null) 
			throw new OfertaLaboralRepetidaException("Ya existe la Oferta Laboral %s".formatted(olExiste.getNombre()));
		
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Tipo t = mt.buscarTipo(nombreTipo);
		
		Map<String, Keyword> keys = new HashMap<>();	
		for(Map.Entry<String, DTKeyword> entry: ol.getKeywords().entrySet()) 
			keys.put(entry.getKey(), mol.buscarKeyword(entry.getKey()));
		
		OfertaLaboral olNueva = new OfertaLaboral(ol.getNombre(), ol.getDescripcion(), ol.getCiudad(), ol.getDepartamente(), 
															ol.getHorario(), ol.getRemuneracion(), ol.getFechaDeAlta(), ol.getCostoAsociado(), t, keys, e);
		
		mol.agregarOfertaLaboral(olNueva);

		e.agregarOfertaLaboral(olNueva);
	}
	
	public void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Paquete p = mt.buscarPaquete(datosPaquete.getNombre());
		
		if(p != null)
			throw new PaqueteRepetidoException("Ya existe el Paquete %s".formatted(datosPaquete.getNombre()));
		
		Paquete nuevoPaquete = new Paquete(datosPaquete.getNombre(), datosPaquete.getDescripcion(), datosPaquete.getPeriodoDeValidez(), 
		datosPaquete.getDescuento(), datosPaquete.getCostoAsociado(), datosPaquete.getFechaDeAlta());
		
		mt.agregarPaquete(nuevoPaquete);
	}

	public void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
        Tipo t = mt.buscarTipo(nombre);
        if (t != null)
            throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        t = new Tipo(nombre, descripcion, exposicionT, duracion, costo, fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        mt.agregarTipo(t);
		}
	
	public void ingresarKeyword(String nombre){
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
        Keyword k = mol.buscarKeyword(nombre);
        if (k == null) {
        //    throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        k = new Keyword(nombre);
        mol.agregarKeyword(k);
        }
}
	
	public String[] listarNomPaquetes() throws NoHayPaquetesException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Map<String, Paquete> mapPaquetes = mt.getMapPaquete();
		if(mapPaquetes.size() == 0)
			throw new NoHayPaquetesException("No hay paquetes registrados");
		
		String[] dp = new String[mapPaquetes.size()];
		int i =0;
		for (Map.Entry<String, Paquete> entry : mapPaquetes.entrySet()) {
			dp[i] = entry.getKey();
			i++;
		}
		return dp;
	}
	
	public String[] listarNomTipos() throws NoHayTiposException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Map<String, Tipo> mapTipos = mt.getMapTipo();
		if(mapTipos.size() == 0)
			throw new NoHayTiposException("No hay tipos registrados");
		
		String[] dt = new String[mapTipos.size()];
		int i =0;
		for (Map.Entry<String, Tipo> entry : mapTipos.entrySet()) {
			dt[i] = entry.getKey();
			i++;
		}
		return dt;
	}
	
	
	public void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Tipo t = mt.buscarTipo(tipTipo);
		Paquete p = mt.buscarPaquete(tipPaquete);
		if(!p.agregarTipo(cantidad, t))
			throw new TipoYaAgragadoException("Ya se habia agregado el tipo " + tipTipo + " al paquete " + tipPaquete);
	}
		
	public DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral) {
		ManejadorOfertaLaboral contOfertaLaboral = ManejadorOfertaLaboral.getInstance();
		DTOfertaLaboral ofLabRes= contOfertaLaboral.buscarOfertaLaboral(OfertaLaboral).getDataOfertaLaboral();
		return ofLabRes;
	}

	
	
}

