package logica;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private float costoAsociado;
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getPeriodoDeValidez() {
		return this.periodoDeValidez;
	}
	
	public float getDescuento() {
		return this.descuento;
	}
	
	public float getCostoAsociado() {
		return this.costoAsociado;
	}
	
	public String toString() {
		return this.nombre;
	}
}
