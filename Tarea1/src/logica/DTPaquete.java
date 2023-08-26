package logica;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private float costoAsociado;
	
	public DTPaquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
	}
	
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

