package logica;

import excepciones.KeywordsNoExistenException;
import excepciones.TipoPubNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenNingunaException;
import excepciones.PaqueteRepetidoException;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.TipoRepetidoException;
import excepciones.TipoYaAgragadoException;

import java.time.LocalDate;
import java.time.ZoneId;

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
	
    public String DatosPaqueteAMostrar(DTPaquete paq) {
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
			throw new OfertaLaboralRepetidaException("Ya existe la Oferta Laboral %s".formatted(olExiste.getNombre()));
		
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Tipo tip = mtTip.buscarTipo(nombreTipo);
		
		Map<String, Keyword> keys = new HashMap<>();	
		for (Map.Entry<String, DTKeyword> entry: ofl.getKeywords().entrySet()) 
			keys.put(entry.getKey(), mol.buscarKeyword(entry.getKey()));
		
		OfertaLaboral olNueva = new OfertaLaboral(ofl.getNombre(), ofl.getDescripcion(), ofl.getCiudad(), ofl.getDepartamento(), 
															ofl.getHorario(), ofl.getRemuneracion(), ofl.getFechaDeAlta(), ofl.getCostoAsociado(), tip, keys, emp, ofl.getImagen());
		
		mol.agregarOfertaLaboral(olNueva);

		emp.agregarOfertaLaboral(olNueva);
	}
	
	public void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException {
		ManejadorTipo mtTip = ManejadorTipo.getInstancia();
		Paquete paq = mtTip.buscarPaquete(datosPaquete.getNombre());
		
		if (paq != null)
			throw new PaqueteRepetidoException("Ya existe el Paquete %s".formatted(datosPaquete.getNombre())); 
		
		Paquete nuevoPaquete = new Paquete(datosPaquete.getNombre(), datosPaquete.getDescripcion(), datosPaquete.getPeriodoDeValidez(), 
		datosPaquete.getDescuento(), 0, datosPaquete.getFechaDeAlta(), datosPaquete.getImagen());
		
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
			if (entry.getValue().getCompra() == null) {
				dpa[iter] = entry.getKey();
				iter++;
			}
		}
		return dpa;
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
	
	public void AcepRechOL(EstadoOL est, String nomOL) {
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
	
	public void comprarPaquete(String empresa, String paquete) {
		ManejadorTipo mtTip =  ManejadorTipo.getInstancia();
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		
		Empresa empresaG = musr.buscarEmpresa(empresa);
		Paquete paqueteG = mtTip.buscarPaquete(paquete);
		
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
	}

	public DTPostulacion dataPostulacion(String nickname, String nombreOL) {
		ManejadorUsuario musu = ManejadorUsuario.getInstancia();
		Postulante post = musu.buscarPostulante(nickname);
		List<Postulacion> postulaciones = post.getPostulaciones();
		DTPostulacion dataPostulacion = null;
		for (Postulacion posts : postulaciones) {
			if(posts.getOfertaLaboral().getNombre().equals(nombreOL)) {
				dataPostulacion = posts.getDataPostulacion();
			}
		}
		
		if (dataPostulacion == null) {
			System.out.println("dtpost null");
		}
		
		return dataPostulacion;
	}
	
}

