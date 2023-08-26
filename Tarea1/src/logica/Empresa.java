package logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class Empresa extends Usuario {
	
	private String nombreEmpresa;
	private String descripcion;
	private String link;
	private Map<String, OfertaLaboral> ofertasLaborales;
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String nombreEmpresa, String descripcion, String link) {
		super(nickname, nombre, apellido, correo);
		this.nombreEmpresa = nombreEmpresa;
		this.descripcion = descripcion;
		this.link = link;
		this.ofertasLaborales = new HashMap<>();
	}
	
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
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
	
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void agregarOfertaLaboral(OfertaLaboral ol) {
		this.ofertasLaborales.put(ol.getNombre(), ol);
	}
	
	public DTEmpresa getDataEmpresaALO() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(),
				this.getCorreo(), this.getNombreEmpresa(),this.getDescripcion(), 
				this.getLink());
//	public DTEmpresa getDataEmpresa() 
	}
	
	public ArrayList<DTOfertaLaboral> obtenerOfertasVigentes() {
        ArrayList<DTOfertaLaboral> dof = new ArrayList<>();
		Map<String, OfertaLaboral> ofertasLaborales = this.ofertasLaborales;
	    if (ofertasLaborales.isEmpty()) { //consigo el array
	        dof= null;
	    } else {
	        Collection<OfertaLaboral> ofs = ofertasLaborales.values();
	        Object[] o = ofs.toArray();
	        OfertaLaboral oferta;
	        
	        for (int i = 0; i < o.length; i++) {
	        	oferta = (OfertaLaboral) o[i];
	        	if(oferta.estaVigente()) {
                dof.add(new DTOfertaLaboral(oferta.getNombre(), oferta.getDescripcion(), oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), oferta.getFechaDeAlta()));
	        	}
	        }
	    }
	    return dof;
	}
	
	public OfertaLaboral buscarOferta(String oferta) {
		return this.ofertasLaborales.get(oferta);
	}
}
