package logica;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
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

	} 
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String descripcion, String link, String contrasenia, byte[] image) {
		super(nickname, nombre, apellido, correo, contrasenia, image);
		this.descripcion = descripcion;
		this.link = link;
		this.ofertasLaborales = new HashMap<>();

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
	
	public void agregarOfertaLaboral(OfertaLaboral ol) {
		this.ofertasLaborales.put(ol.getNombre(), ol);
	}
	
	public List<Compra> getCompras() {
		return this.paqComprados;
	}
	
	public void agregarCompra(Compra compra) {
		this.paqComprados.add(compra);
	}
	
	public DTEmpresa getDataEmpresaALO() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(),
				this.getCorreo(),this.getDescripcion(), 
				this.getLink());
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
	
	public DTOfertaLaboral[] obtenerOfertasIngresadas() {
		Map<String, OfertaLaboral> ofertasLaborales = this.ofertasLaborales;
	    if (ofertasLaborales.isEmpty()) { //consigo el array
	        return null;
	    } else {
	    	OfertaLaboral oferta;
	    	int i =0;
	    	DTOfertaLaboral[] dof = new DTOfertaLaboral[ofertasLaborales.size()];
	    	for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
	        	oferta = entry.getValue();
	        	if(oferta.getEstado() == EstadoOL.Ingresada) {
                dof[i] = new DTOfertaLaboral(oferta.getNombre(), oferta.getDescripcion(), oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), oferta.getFechaDeAlta());
	        	i++;
	        	}
	        }
	    	if(i==0)
	    		return null;
	    	return dof;
	    }
	    
	}
	
	public OfertaLaboral buscarOferta(String oferta) {
		return this.ofertasLaborales.get(oferta);
	}

	public DTEmpresa getDataEmpresa() {
		Map<String, DTOfertaLaboral> ofertasLab = new HashMap<>();
		Map<String, OfertaLaboral> ol = this.getOfertasLaborales();
		if(ol != null) {
			for(Map.Entry<String, OfertaLaboral> entry : ol.entrySet()) {
				ofertasLab.put(entry.getKey(), entry.getValue().getDataOfertaLaboral());
			}
		}
			DTEmpresa dtE = new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), ofertasLab/*, this.getNombreEmpresa()*/, this.getDescripcion(), this.getLink());
			return dtE;
	}
	
	public DTUsuario getDataUsuario() {
		return this.getDataEmpresa();

	}
	
	public void modificarDatos(String nombre, String apellido, String desc, String l) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDescripcion(desc);
		this.setLink(l);
	}
}
