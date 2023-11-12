package logica;

import excepciones.KeywordsNoExistenException;
import excepciones.NoExistenOfertasSeleccionarPostulanteException;
import excepciones.TipoPubNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenNingunaException;
import excepciones.PaqueteRepetidoException;
import excepciones.PaqueteYaCompradoException;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.TipoRepetidoException;
import excepciones.TipoYaAgragadoException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
		
	}
	
	public DTPaquete[] listarPaquetes() throws NoHayPaquetesException {
		ManejadorTipo mtip = ManejadorTipo.getInstancia();
		Paquete[] paquetes = mtip.getPaquetes();
		
		if (paquetes != null) {
			DTPaquete[] dtpaq = new DTPaquete[paquetes.length];
			Paquete paq;
			
			for (int i = 0; i <paquetes.length; i++) {
				paq = paquetes[i];
				dtpaq[i] = paq.getDataPaquete();
			}
			
			return dtpaq;
		} else {
			throw new NoHayPaquetesException("No hay paquetes registrados");
		}
	}
	
    public String datosPaqueteAMostrar(DTPaquete paq) {
    	return "Nombre: " + paq.getNombre() + "\nDescripcion: " + paq.getDescripcion() + "\nPeriodo de validez: " + paq.getPeriodoDeValidez() + " días\nDescuento: " + paq.getDescuento() + "%\nCosto: $" + paq.getCostoAsociado();
    }

	public DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException {
		ManejadorTipo mtip = ManejadorTipo.getInstancia();
		Tipo[] tipos = mtip.getTipos();
		
		if (tipos != null) {
			DTTipo[] dtip = new DTTipo[tipos.length];
			Tipo tipo;
			
			for (int i = 0; i <tipos.length; i++) {
				tipo = tipos[i];
				dtip[i] = tipo.getDataTipo();
			}
			
			return dtip;
		} else 
			throw new TipoPubNoExistenException("No existen Tipos de Publicaciones de Ofertas Laborales registradas");
	}
	
	public String[] listarKeywords() throws KeywordsNoExistenException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		
		if (keys != null) {
			String[] kArray = new String[keys.length];
			Keyword key;
			
			for (int i = 0; i < keys.length; i++) {
				key = keys[i];
				kArray[i] = key.getNombre();
			}
			
			return kArray;
		} else 
			throw new KeywordsNoExistenException("No existen Keywords registradas");
	}
	
	public void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ofl) throws OfertaLaboralRepetidaException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral olExiste = mol.buscarOfertaLaboral(ofl.getNombre());
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Empresa emp = musr.buscarEmpresa(empresa);
		if (olExiste != null) 
			throw new OfertaLaboralRepetidaException("Ya existe la Oferta Laboral " + olExiste.getNombre());
		
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Tipo tip = mtTip.buscarTipo(nombreTipo);
		
		DTKeyword[] dtk = ofl.getKeywords();
		
		Map<String, Keyword> keys = new HashMap<>();	
		for (int i = 0 ; i < dtk.length ; i++) 
			keys.put(dtk[i].getNombre(), mol.buscarKeyword(dtk[i].getNombre()));
		
		 // Crea un objeto DateTimeFormatter con el formato de tu cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parsea la cadena a LocalDate sin bloque try-catch
        LocalDate date = LocalDate.parse(ofl.getFechaDeAlta(), formatter);
		OfertaLaboral olNueva = new OfertaLaboral(ofl.getNombre(), ofl.getDescripcion(), ofl.getCiudad(), ofl.getDepartamento(), 
															ofl.getHorario(), ofl.getRemuneracion(), date, ofl.getCostoAsociado(), tip, keys, emp, ofl.getImagen());
		
		mol.agregarOfertaLaboral(olNueva);

		emp.agregarOfertaLaboral(olNueva);
	}
	
	public void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException {
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Paquete paq = mtTip.buscarPaquete(datosPaquete.getNombre());
		
		if (paq != null)
			throw new PaqueteRepetidoException("Ya existe el Paquete " + datosPaquete.getNombre()); 
		
		String fechaDeAltaString = datosPaquete.getFechaDeAlta();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaDeAlta = LocalDate.parse(fechaDeAltaString, formatter);
		
		Paquete nuevoPaquete = new Paquete(datosPaquete.getNombre(), datosPaquete.getDescripcion(), datosPaquete.getPeriodoDeValidez(), 
		datosPaquete.getDescuento(), 0, fechaDeAlta, datosPaquete.getImagen());
		
		mtTip.agregarPaquete(nuevoPaquete);
	}

	public void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException{
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
        Tipo tip = mtTip.buscarTipo(nombre);
        if (tip != null)
            throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        tip = new Tipo(nombre, descripcion, exposicionT, duracion, costo, fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        mtTip.agregarTipo(tip);
		}
	
	public void ingresarKeyword(String nombre){
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
        Keyword key = mol.buscarKeyword(nombre);
        if (key == null) {
        //    throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        key = new Keyword(nombre);
        mol.agregarKeyword(key);
        }
}
	
	public String[] listarNomPaquetes() throws NoHayPaquetesException{
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Map<String, Paquete> mapPaquetes = mtTip.getMapPaquete();
		if (mapPaquetes.size() == 0)
			throw new NoHayPaquetesException("No hay paquetes registrados");
		
		String[] dpa = new String[mapPaquetes.size()];
		int iter =0;
		for (Map.Entry<String, Paquete> entry : mapPaquetes.entrySet()) {
			//if (entry.getValue().getCompra() == null) {
			dpa[iter] = entry.getKey();
			iter++;
			//}
		}
		return dpa;
	}
	
	public String[] listarNomPaquetesNoComprados() throws NoHayPaquetesException{
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Map<String, Paquete> mapPaquetes = mtTip.getMapPaquete();
		if (mapPaquetes.size() == 0)
			throw new NoHayPaquetesException("No hay paquetes registrados");
		
		List<String> dpa = new ArrayList<>();
		int iter =0;
		for (Map.Entry<String, Paquete> entry : mapPaquetes.entrySet()) {
			if (entry.getValue().getCompra() == null || entry.getValue().getCompra().size() == 0) {
				dpa.add(entry.getKey());
			}
		}
		
		if (dpa != null && dpa.size() != 0) {
		   	 return dpa.toArray(new String[dpa.size()]);
		}else {
			throw new NoHayPaquetesException("No hay paquetes no comprados");
		}
	}
	
	public String[] listarNomTipos() throws NoHayTiposException{
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Map<String, Tipo> mapTipos = mtTip.getMapTipo();
		if (mapTipos.size() == 0)
			throw new NoHayTiposException("No hay tipos registrados");
		
		String[] dtTip = new String[mapTipos.size()];
		int iter =0;
		for (Map.Entry<String, Tipo> entry : mapTipos.entrySet()) {
			dtTip[iter] = entry.getKey();
			iter++;
		}
		return dtTip;
	}
	
	
	public void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException{
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Tipo tip = mtTip.buscarTipo(tipTipo);
		Paquete paq = mtTip.buscarPaquete(tipPaquete);
		if (!paq.agregarTipo(cantidad, tip))
			throw new TipoYaAgragadoException("Ya se habia agregado el tipo " + tipTipo + " al paquete " + tipPaquete);
	}
		
	public DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral) {
		ManejadorOfertaLaboral contOfertaLaboral = ManejadorOfertaLaboral.getInstance();
		DTOfertaLaboral ofLabRes = contOfertaLaboral.buscarOfertaLaboral(OfertaLaboral).getDataOfertaLaboral();
		return ofLabRes;
	}
	
	public void acepRechOL(EstadoOL est, String nomOL) {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral oferta = mol.buscarOfertaLaboral(nomOL);
		oferta.setEstado(est);
	}

	public DTOfertaLaboral[] listarTodasOfertasLaborales() throws OfertasLaboralesNoExistenNingunaException{
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Map<String, OfertaLaboral> ols = mol.getOfertasLaborales();
		if (ols.size() != 0) {
			DTOfertaLaboral[] todasOls = new DTOfertaLaboral[ols.size()];
			int iter = 0;
			for (Map.Entry<String, OfertaLaboral> entry : ols.entrySet()) {
				todasOls[iter] = entry.getValue().getDataOfertaLaboral();
				iter++;
			}
			return todasOls;
		} else {
			throw new OfertasLaboralesNoExistenNingunaException("No existen Ofertas Laborales");
		}
	}
	

	public void comprarPaquete(String empresa, String paquete) throws PaqueteYaCompradoException{
		ManejadorTipo mtTip =  ManejadorTipo.getInstancia();
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Empresa empresaG = musr.buscarEmpresa(empresa);
		Paquete paqueteG = mtTip.buscarPaquete(paquete);
		

		if (empresaG.verificarCompra(paquete)) {
			
			LocalDate fechaCompra = LocalDate.now();
			int periodoValidezPaq = paqueteG.getPeriodoDeValidez();
			LocalDate vencimiento = fechaCompra.plusDays(periodoValidezPaq);
			
			List<PaqueteTipo> paqTip = paqueteG.getPaquetesTipos();
			List<CompraTipo>  compTip = new LinkedList<>();
			for (PaqueteTipo p : paqTip) {
				CompraTipo ctip = new CompraTipo(p.getCantidad() , p.getTipo());
				compTip.add(ctip);
			}
			Compra compra = new Compra(fechaCompra, vencimiento, paqueteG, empresaG, compTip);
			empresaG.agregarCompra(compra);
		}else {
			throw new PaqueteYaCompradoException("Ya se ha comprado ese paquete");
		}

	}

	public DTPostulacion dataPostulacion(String nickname, String nombreOL) {
		ManejadorUsuario musu = ManejadorUsuario.getInstancia();
		Postulante post = musu.buscarPostulante(nickname);
		List<Postulacion> postulaciones = post.getPostulaciones();
		DTPostulacion dataPostulacion = null;
		for (Postulacion posts : postulaciones) {
			if (posts.getOfertaLaboral().getNombre().equals(nombreOL)) {
				dataPostulacion = posts.getDataPostulacion();
			}
		}
		
		return dataPostulacion;
	}
	
	public DTOfertaLaboral obtenerDTOfertaLaboral(String oferta) {
		DTOfertaLaboral dtoferta = ManejadorOfertaLaboral.getInstance().buscarOfertaLaboral(oferta).getDataOfertaLaboral();
		return dtoferta;
	}

	public DTKeyword[] getDTKeywords(){
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		DTKeyword[] dtkeys = new DTKeyword[keys.length];
		for(int i = 0; i < keys.length; i++)
			dtkeys[i] = new DTKeyword(keys[i].getNombre());
		
		return dtkeys;
	}
	
	public DTPaquete buscarPaquete(String nombre) {
		return ManejadorTipo.getInstancia().buscarPaquete(nombre).getDataPaquete();
	}
	
	public DTOfertaLaboral[] getOfertasSeleccionarPosutlante(String nick) throws NoExistenOfertasSeleccionarPostulanteException {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Empresa emp = musr.buscarEmpresa(nick);
		Map<String, OfertaLaboral> ofertas = emp.getOfertasLaborales();
		int cantOfertas = 0;
		for(Map.Entry<String, OfertaLaboral> entry : ofertas.entrySet()) {
			if(!entry.getValue().estaVigente() && entry.getValue().getEstado() == EstadoOL.Confirmada)
				cantOfertas++;
		}
		if (cantOfertas == 0)
			throw new NoExistenOfertasSeleccionarPostulanteException("No existen ofertas para selccionar postulaciones");
		else {
			int i = 0;
			DTOfertaLaboral[] dtsOL = new DTOfertaLaboral[cantOfertas];
			for(Map.Entry<String, OfertaLaboral> entry : ofertas.entrySet()) {
				if(!entry.getValue().estaVigente() && entry.getValue().getEstado() == EstadoOL.Confirmada) {
					dtsOL[i] = entry.getValue().getDataOfertaLaboral();
					i++;
				}
			}
			
			return dtsOL;
		}
	}
	
	public DTOfertaLaboral buscarOfertaLaboral(String nombre) {
		ManejadorOfertaLaboral col = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral oflab = col.buscarOfertaLaboral(nombre);
		return oflab.getDataOfertaLaboral();
	}
	
	public boolean estaVigenteOferta(String nombreOferta) {
    	ManejadorOfertaLaboral mofe = ManejadorOfertaLaboral.getInstance();
    	OfertaLaboral oferta = mofe.buscarOfertaLaboral(nombreOferta);
    	return oferta.estaVigente();
	}
	
	public void realizarSeleccion(String nombreOL, String[] rankings) {
    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
    	OfertaLaboral oferta = mol.buscarOfertaLaboral(nombreOL);
    	oferta.realizarSeleccion(rankings);
	}
	
	public DTTipo buscarTipo(String nombre) {
    	ManejadorTipo mti = ManejadorTipo.getInstancia();
    	return mti.buscarTipo(nombre).getDataTipo();
	}

	public String obtenerVideoPostulacion(String nickname, String oferta) {
		DTPostulacion pos = dataPostulacion(nickname, oferta);
		return pos.obtenerIDDeVideo();
	};

}

