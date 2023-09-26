package com.trabajouy.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class Compra {
	private LocalDate fechaCompra;
	private LocalDate fechaVencimiento;
	private Paquete paqComprado;
	private Map<String, OfertaLaboral> ofertasLaborales;
	
	public Compra() {
		fechaCompra = null;
		fechaVencimiento = null;
		paqComprado = null;
		ofertasLaborales = new HashMap<>();
	}
	
	public Compra(LocalDate fComp, LocalDate fVenc, Paquete p, Map<String, OfertaLaboral> ofLab) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = p;
		ofertasLaborales = ofLab;
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
	
	public Map<String, OfertaLaboral> getOfertasLaboralesCompra(){
		return ofertasLaborales;
	}
}
