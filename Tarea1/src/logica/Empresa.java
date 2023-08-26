package logica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
		ofertasLaborales = new HashMap<>();
	}
	
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}
	
	public String getDescripcion() {
		return this.descripcion;
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
	
	public Map<String, OfertaLaboral> getOfertasLaborales(){
		return this.ofertasLaborales;
	}
	/*public void agregarOfertaLaboral(OfertaLaboral ol) {
	}*/
	
	public DTEmpresa getDataEmpresa() {
		Map<String, DTOfertaLaboral> ofertasLab = new HashMap<>();
		Map<String, OfertaLaboral> ol = this.getOfertasLaborales();
		if(ol.size() != 0) {
			for(Map.Entry<String, OfertaLaboral> entry : ol.entrySet()) {
				ofertasLab.put(entry.getKey(), entry.getValue().getDataOfertaLaboral());
			}
		}
			DTEmpresa dtE = new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), ofertasLab, this.getNombreEmpresa(), this.getDescripcion(), this.getLink());
			return dtE;
	}
	
	public DTUsuario getDataUsuario() {
		return this.getDataEmpresa();

	}
	
	public void modificarDatos(String nombre, String apellido, String nomEmp, String desc, String l) {
		//Usuario u = (Usuario) this;
		this.setNombre(nombre);
		this.setApellido(apellido);
		//u.setNombre(nombre);
		//u.setApellido(apellido);
		this.setNombreEmpresa(nomEmp);
		this.setDescripcion(desc);
		this.setLink(l);
	}
}
