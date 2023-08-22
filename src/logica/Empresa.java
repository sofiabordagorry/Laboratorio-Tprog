package logica;

import java.util.Map;

public class Empresa extends Usuario {
	
	private String nombreEmpresa;
	private String descripcion;
	private String link;
	//private Map<String, OfertaLaboral> ofertasLaborales;
	
	public Empresa(String nickname, String nombre, String apellido, String correo, String nombreEmpresa, String descripcion, String link) {
		super(nickname, nombre, apellido, correo);
		this.nombreEmpresa = nombreEmpresa;
		this.descripcion = descripcion;
		this.link = link;
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
	
	/*public void agregarOfertaLaboral(OfertaLaboral ol) {
	}
	
	public DTEmpresa getDataEmpresa() {
	}*/
}
