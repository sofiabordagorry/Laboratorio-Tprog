package com.trabajouy.model;

import com.trabajouy.exceptions.KeywordsNoExistenException;
import com.trabajouy.exceptions.TipoPubNoExistenException;
import com.trabajouy.exceptions.OfertaLaboralRepetidaException;
import com.trabajouy.exceptions.OfertasLaboralesNoExistenNingunaException;
import com.trabajouy.exceptions.PaqueteRepetidoException;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.trabajouy.exceptions.NoHayPaquetesException;
import com.trabajouy.exceptions.NoHayTiposException;
import com.trabajouy.exceptions.TipoRepetidoException;
import com.trabajouy.exceptions.TipoYaAgragadoException;

import java.time.ZoneId;

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
		
		OfertaLaboral olNueva = new OfertaLaboral(ol.getNombre(), ol.getDescripcion(), ol.getCiudad(), ol.getDepartamento(), 
												ol.getHorario(), ol.getRemuneracion(), ol.getFechaDeAlta(), ol.getCostoAsociado(), t, keys, e, ol.getImagen());
		
		mol.agregarOfertaLaboral(olNueva);

		e.agregarOfertaLaboral(olNueva);
	}
	
	public void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Paquete p = mt.buscarPaquete(datosPaquete.getNombre());
		
		if(p != null)
			throw new PaqueteRepetidoException("Ya existe el Paquete %s".formatted(datosPaquete.getNombre())); 
		
		Paquete nuevoPaquete = new Paquete(datosPaquete.getNombre(), datosPaquete.getDescripcion(), datosPaquete.getPeriodoDeValidez(), 
		datosPaquete.getDescuento(), 0, datosPaquete.getFechaDeAlta(), datosPaquete.getImagen());
		
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
			if(entry.getValue().getCompra() == null) {
				dp[i] = entry.getKey();
				i++;
			}
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
		DTOfertaLaboral ofLabRes = contOfertaLaboral.buscarOfertaLaboral(OfertaLaboral).getDataOfertaLaboral();
		return ofLabRes;
	}
	
	public void AcepRechOL(EstadoOL e, String OL) {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral oferta = mol.buscarOfertaLaboral(OL);
		oferta.setEstado(e);
	}

	public DTOfertaLaboral[] listarTodasOfertasLaborales() throws OfertasLaboralesNoExistenNingunaException{
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Map<String, OfertaLaboral> ols = mol.getOfertasLaborales();
		if(ols.size() == 0) {
			System.out.println("ninguna ol");
		}
		if(ols.size() != 0) {
			DTOfertaLaboral[] todasOls = new DTOfertaLaboral[ols.size()];
			int i = 0;
			for(Map.Entry<String, OfertaLaboral> entry : ols.entrySet()) {
				if(entry.getValue().getEstado() == EstadoOL.Aceptada)
					todasOls[i] = entry.getValue().getDataOfertaLaboral();
				i++;
			}
			return todasOls;
		} else {
			throw new OfertasLaboralesNoExistenNingunaException("No existen Ofertas Laborales");
		}
	}
	
	public DTPostulacion dataPostulacion(String nickname, String nombreOL) {
		ManejadorUsuario musu = ManejadorUsuario.getInstancia();
		Postulante post = musu.buscarPostulante(nickname);
		LinkedList<Postulacion> postulaciones = post.getPostulaciones();
		DTPostulacion dataPostulacion = null;
		for(Postulacion posts : postulaciones) {
			if(posts.getOfertaLaboral().getNombre().equals(nombreOL)) {
				dataPostulacion = posts.getDataPostulacion();
			}
		}
		
		if(dataPostulacion == null) {
			System.out.println("dtpost null");
		}
		
		return dataPostulacion;
	}
	
	/*public List<Paquete> obtenerPaquetesParaCompraOL(Tipo tipo, Empresa emp) {
		List<Paquete> paqs = new ArrayList<>();
		List<Compra> compras = emp.getCompras();
		for (int i = 0; i < compras.size(); i++) {
			Paquete paq = compras.get(i).getPaquetesPorTipo(tipo);
			if (paq != null) {
				paqs.add(paq);
			}
			
		}
		return paqs;
	}
	
	public void comprarTipo(Tipo tipo, Empresa emp) {
		List<Paquete> paqs = new ArrayList<>();
		List<Compra> compras = emp.getCompras();
		for (int i = 0; i < compras.size(); i++) {
			compras.get(i).restarCompraPorTipo(tipo);
			
		}
	}*/
	
}

