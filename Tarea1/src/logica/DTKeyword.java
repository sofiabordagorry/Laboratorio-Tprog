package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTKeyword {
	private String nombre;
	
	public DTKeyword() {}
	
	// ---------------- Constructores ------------------
	public DTKeyword(String nombre) {
		this.nombre = nombre;
	}
	
	// ------------ Getters --------------
	public String getNombre() {
		return this.nombre;
	}
	
	public String toString() {
		return this.nombre;
	}
	
	// ------------ Setters --------------
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
