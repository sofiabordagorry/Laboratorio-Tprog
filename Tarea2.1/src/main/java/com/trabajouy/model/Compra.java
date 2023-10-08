package com.trabajouy.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Compra {
	private LocalDate fechaCompra;
	private LocalDate fechaVencimiento;
	private Paquete paqComprado;
	private Empresa empresa;
	private List<CompraTipo> comprasTipos;
	
	public Compra() {
		fechaCompra = null;
		fechaVencimiento = null;
		paqComprado = null;
		empresa= null;
		comprasTipos = null;
	}
	
	public Compra(LocalDate fComp, LocalDate fVenc, Paquete p, Empresa empre, List<CompraTipo> comprasTipou) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = p;
		empresa=empre;
		comprasTipos=comprasTipou;
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public Paquete getPaqueteComprado() {
		return paqComprado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	
	public List<CompraTipo> getCompraTipo(){
		return comprasTipos;
	}
	
	public Paquete getPaquetesPorTipo(Tipo tipo){
		List<CompraTipo> ctip = this.getCompraTipo();
		for (int j = 0; j < ctip.size(); j++) {
			if(this.getFechaVencimiento().isAfter(LocalDate.now())) { //no esta vencida
				if (ctip.get(j).tieneTipoDisp(tipo)) {// hay paquetes con tipo que quiero
					return this.getPaqueteComprado();
				}
			}
		}
		return null;
	}
	public void restarCompraPorTipo(Tipo tipo) {
		List<CompraTipo> ctip = this.getCompraTipo();
		for (int j = 0; j < ctip.size(); j++) {
			ctip.get(j).restarTipo(tipo);
		}
	}
}
