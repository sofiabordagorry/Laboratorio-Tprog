package logica;

public class PaqueteTipo {
	private int cantidad;
	private Tipo tipo;
	
	public PaqueteTipo(int cantidad, Tipo tipo) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public DTPaqueteTipo getDTPaqueteTipo() {
		return new DTPaqueteTipo(this.cantidad, this.tipo.getDataTipo());
	}
}