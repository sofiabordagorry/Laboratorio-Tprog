package com.trabajouy.model;

import com.trabajouy.exceptions.TipoPubNoExistenException;
import com.trabajouy.exceptions.KeywordsNoExistenException;
import com.trabajouy.exceptions.OfertaLaboralRepetidaException;
import com.trabajouy.exceptions.OfertasLaboralesNoExistenNingunaException;
import com.trabajouy.exceptions.PaqueteRepetidoException;

import java.util.Date;
import com.trabajouy.exceptions.TipoRepetidoException;
import com.trabajouy.exceptions.NoHayTiposException;
import com.trabajouy.exceptions.NoHayPaquetesException;
import com.trabajouy.exceptions.TipoYaAgragadoException;


public interface IOfertaLaboral {
	public abstract DTPaquete[] listarPaquetes() throws NoHayPaquetesException;
	public abstract String DatosPaqueteAMostrar(DTPaquete p);
	public abstract DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException;
	public abstract String[] listarKeywords() throws KeywordsNoExistenException;
	public abstract void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ol) throws OfertaLaboralRepetidaException;
	public abstract void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException;
	public abstract void AcepRechOL(EstadoOL e, String OL);
	public abstract void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException;
	public abstract void ingresarKeyword(String nombre);
	public abstract String[] listarNomPaquetes() throws NoHayPaquetesException;
	public abstract String[] listarNomTipos() throws NoHayTiposException;
	public abstract void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException;
	public abstract DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral);
	public abstract DTOfertaLaboral[] listarTodasOfertasLaborales() throws OfertasLaboralesNoExistenNingunaException;
	public abstract DTPostulacion dataPostulacion(String nickname, String nombreOL);

}
