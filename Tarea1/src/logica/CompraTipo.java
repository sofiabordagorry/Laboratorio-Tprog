package logica;

public class CompraTipo{
	private int cantidad;
	private Tipo tipoP;
	
	public CompraTipo(int canti, Tipo tipoPa) {
		setCantidad(canti);
		setTipoP(tipoPa);
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Tipo getTipoP() {
		return tipoP;
	}

	public void setTipoP(Tipo tipoP) {
		this.tipoP = tipoP;
	}

}

