package logica;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Empresa extends Usuario {
	
	private String descripcion;
	private String link;
	private Map<String, OfertaLaboral> ofertasLaborales;
	private List<Compra> paqComprados;
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String descripcion, String link) {
		super(nickname, nombre, apellido, correo);
		this.descripcion = descripcion;
		this.link = link;
		this.ofertasLaborales = new HashMap<>();

	}
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String descripcion, String link, String contrasenia) {
		super(nickname, nombre, apellido, correo, contrasenia);
		this.descripcion = descripcion;
		this.link = link;
		this.ofertasLaborales = new HashMap<>();
		this.paqComprados = new ArrayList<>();

	} 
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String descripcion, String link, String contrasenia, byte[] image) {
		super(nickname, nombre, apellido, correo, contrasenia, image);
		this.descripcion = descripcion;
		this.link = link;
		this.ofertasLaborales = new HashMap<>();
		this.paqComprados = new ArrayList<>();
	} 
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Map<String, OfertaLaboral> getOfertasLaborales() {
		return this.ofertasLaborales;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void agregarOfertaLaboral(OfertaLaboral ofertal) {
		this.ofertasLaborales.put(ofertal.getNombre(), ofertal);
	}
	
	public List<Compra> getCompras() {
		return this.paqComprados;
	}
	
	public void agregarCompra(Compra compra) {
		if (this.paqComprados == null) {
			this.paqComprados = new ArrayList<>();
		}
		this.paqComprados.add(compra);
	}
	
	public DTEmpresa getDataEmpresaALO() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(),
				this.getCorreo(), null, null, this.getDescripcion(), 
				this.getLink(), null, null);
	}
	
	
	public List<DTOfertaLaboral> obtenerOfertasVigentes() {
        List<DTOfertaLaboral> dof = new ArrayList<>();
		Map<String, OfertaLaboral> ofertasLaborales = this.ofertasLaborales;
	    if (ofertasLaborales.isEmpty()) { //consigo el array
	        dof= null;
	    } else {
	        Collection<OfertaLaboral> ofs = ofertasLaborales.values();
	        Object[] ofertas = ofs.toArray();
	        OfertaLaboral oferta;
	        
	        for (int i = 0; i < ofertas.length; i++) {
	        	oferta = (OfertaLaboral) ofertas[i];
	        	if (oferta.estaVigente()) {
	        	      // Crea un objeto DateTimeFormatter con el formato deseado
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                // Convierte el LocalDate a una cadena
	                String dateString = oferta.getFechaDeAlta().format(formatter);
	                dof.add(new DTOfertaLaboral(oferta.getNombre(), oferta.getDescripcion(), oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), dateString));
	        	}
	        }
	    }
	    return dof;
	}
	
	public List<DTOfertaLaboral> obtenerOfertasVigentesyConfirmadas() {
        List<DTOfertaLaboral> dof = new ArrayList<>();
		Map<String, OfertaLaboral> ofertasLaborales = this.ofertasLaborales;
	    if (ofertasLaborales.isEmpty()) { //consigo el array
	        dof= null;
	    } else {
	        Collection<OfertaLaboral> ofs = ofertasLaborales.values();
	        Object[] ofertas = ofs.toArray();
	        OfertaLaboral oferta;
	        
	        for (int i = 0; i < ofertas.length; i++) {
	        	oferta = (OfertaLaboral) ofertas[i];
	        	if (oferta.estaVigente() && oferta.getEstado() == EstadoOL.Confirmada) {
	        	      // Crea un objeto DateTimeFormatter con el formato deseado
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                // Convierte el LocalDate a una cadena
	                String dateString = oferta.getFechaDeAlta().format(formatter);
	                dof.add(new DTOfertaLaboral(oferta.getNombre(), oferta.getDescripcion(), oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), dateString));
	        	}
	        }
	    }
	    return dof;
	}
	
	public DTOfertaLaboral[] obtenerOfertasIngresadas() {
		Map<String, OfertaLaboral> ofertasLaborales = this.ofertasLaborales;
	    if (ofertasLaborales.isEmpty()) { //consigo el array
	        return null;
	    } else {
	    	OfertaLaboral oferta;
	    	int cont =0;
	    	DTOfertaLaboral[] dof = new DTOfertaLaboral[ofertasLaborales.size()];
	    	for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
	        	oferta = entry.getValue();
	        	if (oferta.getEstado() == EstadoOL.Ingresada) {
	        		// Crea un objeto DateTimeFormatter con el formato deseado
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                // Convierte el LocalDate a una cadena
	                String dateString = oferta.getFechaDeAlta().format(formatter);
	                dof[cont] = new DTOfertaLaboral(oferta.getNombre(), oferta.getDescripcion(), oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), dateString);
		        	cont++;
	        	}
	        }
	    	if (cont==0)
	    		return null;
	    	return dof;
	    }
	   
	}
	
	public OfertaLaboral buscarOferta(String oferta) {
		return this.ofertasLaborales.get(oferta);
	}

	public DTEmpresa getDataEmpresa() {
		Map<String, OfertaLaboral> oferl = this.getOfertasLaborales();
		DTOfertaLaboral[] dtsOL;
		if (oferl != null) {
			dtsOL = new DTOfertaLaboral[oferl.size()];
			int i = 0;
			for (Map.Entry<String, OfertaLaboral> entry : oferl.entrySet()) {
				dtsOL[i] = entry.getValue().getDataOfertaLaboral();
				i++;
			}
		} else 
			dtsOL = new DTOfertaLaboral[0];
		List<DTCompra> paquetesComprados = new LinkedList<DTCompra>();
		for (Compra comp : this.paqComprados) {
			paquetesComprados.add(comp.getDataCompra());
		}
			DTEmpresa dtE = new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia(), dtsOL, this.getDescripcion(), this.getLink(), paquetesComprados, this.getImage());
			return dtE;
	}
	
	public DTUsuario getDataUsuario() {
		return this.getDataEmpresa();

	}
	
	public void modificarDatos(String nombre, String apellido, String desc, String link) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDescripcion(desc);
		this.setLink(link);
	}
	
	public boolean verificarCompra(String paquete) {
		List<Compra> compras = this.getCompras();
		boolean sePuede = true;
		if (compras != null) {
			for (int i = 0; i < compras.size(); i++) {
				if (compras.get(i).getPaqueteComprado().getNombre().equals(paquete)) {
					//ya se compro el paquete
					sePuede = false;
				}
			}
		}
		return sePuede;
	}
}
