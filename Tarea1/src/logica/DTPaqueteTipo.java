package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)

public class DTPaqueteTipo {
	private int cantidad;
	private DTTipo tipo;
	
	public DTPaqueteTipo() {
		
	}
	
	public DTPaqueteTipo(int cantidad, DTTipo tipo) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	// --- setters ---
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public DTTipo getTipo() {
		return this.tipo;
	}
	
	public String toString() {
		return "Nombre tipo: " + this.tipo.getNombre() + " | Cantidad: " + this.cantidad;
	}
	
	// --- getters ---
	
	public void setCantidad(int cantidad) {
	    this.cantidad = cantidad;
	}

	public void setTipo(DTTipo tipo) {
	    this.tipo = tipo;
	}

}
