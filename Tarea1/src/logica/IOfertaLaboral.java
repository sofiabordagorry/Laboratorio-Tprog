package logica;


import excepciones.TipoPubNoExistenException;
import excepciones.KeywordsNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteRepetidoException;

import java.util.Date;
import excepciones.TipoRepetidoException;
import excepciones.NoHayTiposException;
import excepciones.NoHayPaquetesException;
import excepciones.TipoYaAgragadoException;


public interface IOfertaLaboral {
	public abstract DTPaquete[] listarPaquetes();
	public abstract String DatosPaqueteAMostrar(DTPaquete p);
	public abstract DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException;
	public abstract String[] listarKeywords() throws KeywordsNoExistenException;
	public abstract void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ol) throws OfertaLaboralRepetidaException;
	public abstract void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException;

	public abstract void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException;
	
	public abstract void ingresarKeyword(String nombre);
	
	public abstract String[] listarNomPaquetes() throws NoHayPaquetesException;
	
	public abstract String[] listarNomTipos() throws NoHayTiposException;
	
	public abstract void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException;
}
