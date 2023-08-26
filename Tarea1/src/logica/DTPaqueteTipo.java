package logica;

public class DTPaqueteTipo {
	private int cantidad;
	private DTTipo tipo;
	
	public DTPaqueteTipo(int cantidad, DTTipo tipo) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public DTTipo getTipo() {
		return this.tipo;
	}
	
	public String toString() {
		return "Nombre tipo: " + this.tipo.getNombre() + " | Cantidad: " + this.cantidad;
	}
}
