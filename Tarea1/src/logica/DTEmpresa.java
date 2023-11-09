package logica;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTEmpresa extends DTUsuario {
	private String descripcion;
	private String link;
	private List<DTCompra>  paqComprados = new ArrayList<>();
	
	public DTEmpresa() {
		super();
	}
	
	// ---------------- Constructores ------------------
	
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, String contrasenia, DTOfertaLaboral[] ofertasLaborales, String descripcion, String link, List<DTCompra> paquetesComprados, byte[] image) {
		super(nickname, nombre, apellido, correo, ofertasLaborales, contrasenia, null);
		this.descripcion = descripcion;
		this.link = link;
		this.paqComprados = paquetesComprados;
	}
		
	// ------------ Getters --------------
	
	public String toString() {
		return this.getNombre() + this.getApellido() + "(" + this.getCorreo() + ")";
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public List<DTCompra> getPaqComprados() {
		return paqComprados;
	}
	
	// ------------ Setters --------------
	
	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setLink(String link) {
	    this.link = link;
	}

	public void setPaqComprados(List<DTCompra> paqComprados) {
	    this.paqComprados = paqComprados;
	}
}
