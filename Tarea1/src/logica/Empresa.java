package logica;

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
	}
}
