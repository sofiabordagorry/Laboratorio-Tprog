package com.trabajouy.model;

import java.util.Map;

public class DTEmpresa extends DTUsuario {
	private String nombreDeEmpresa;
	private String descripcion;
	private String link;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String nombreDeEmpresa, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, ofertasLaborales);
		this.nombreDeEmpresa = nombreDeEmpresa;
		this.descripcion = descripcion;
		this.link = link;
	}

	public DTEmpresa(String nickname, String nombre, String apellido, String correo, String nombreDeEmpresa, String descripcion, String link) {
		super(nickname, nombre, apellido, correo);
		this.nombreDeEmpresa = nombreDeEmpresa;
		this.descripcion = descripcion;
		this.link = link;
	}
	
	public String toString() {
		return this.nombreDeEmpresa + "(" + this.getCorreo() + ")";
	}
	
	public String getNombreDeEmpresa() {
		return this.nombreDeEmpresa;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getLink() {
		return this.link;
	}
}
