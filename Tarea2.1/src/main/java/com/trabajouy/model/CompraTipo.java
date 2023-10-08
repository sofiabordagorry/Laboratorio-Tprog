package com.trabajouy.model;


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
	
	public void restarCant() {
		this.cantidad -= 1;
	}
	
	public boolean tieneTipoDisp(Tipo tipo) {
		if (this.getTipoP() == tipo) {
			if (this.getCantidad() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void restarTipo(Tipo tipo) {
		if (this.getTipoP() == tipo) {
			if (this.getCantidad() > 0) {
				this.restarCant();
			}
		}
	}

}

