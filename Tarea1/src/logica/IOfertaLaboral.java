package logica;

import excepciones.TipoPubNoExistenException;
import excepciones.KeywordsNoExistenException;
import excepciones.NoExistenOfertasSeleccionarPostulanteException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenNingunaException;
import excepciones.PaqueteRepetidoException;
import excepciones.PaqueteYaCompradoException;

import java.util.Date;
import excepciones.TipoRepetidoException;
import excepciones.NoHayTiposException;
import excepciones.NoHayPaquetesException;
import excepciones.TipoYaAgragadoException;


public interface IOfertaLaboral {
	public abstract DTPaquete[] listarPaquetes() throws NoHayPaquetesException;
	public abstract String datosPaqueteAMostrar(DTPaquete paquete);
	public abstract DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException;
	public abstract String[] listarKeywords() throws KeywordsNoExistenException;
	public abstract void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ofl) throws OfertaLaboralRepetidaException;
	public abstract void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException;
	
	public abstract void acepRechOL(EstadoOL estado, String OfertaL);

	public abstract void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException;
	
	public abstract void ingresarKeyword(String nombre);
	
	public abstract String[] listarNomPaquetes() throws NoHayPaquetesException;
	
	public abstract String[] listarNomTipos() throws NoHayTiposException;
	
	public abstract void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException;
	public abstract DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral);
	public abstract DTOfertaLaboral[] listarTodasOfertasLaborales() throws OfertasLaboralesNoExistenNingunaException;
	public abstract DTPostulacion dataPostulacion(String nicknameP, String nombre);
	public abstract void comprarPaquete(String empresa, String paquete) throws PaqueteYaCompradoException;
	public abstract DTOfertaLaboral obtenerDTOfertaLaboral(String ofertaConsultada);
	public abstract DTKeyword[] getDTKeywords();
	public abstract DTPaquete buscarPaquete(String nombre);
	public abstract DTOfertaLaboral[] getOfertasSeleccionarPosutlante(String nick) throws NoExistenOfertasSeleccionarPostulanteException;
	public abstract DTOfertaLaboral buscarOfertaLaboral(String nombre);
	public abstract boolean estaVigenteOferta(String nombreOferta);
	public abstract void realizarSeleccion(String nombreOL, String[] rankings);
	public abstract DTTipo buscarTipo(String nombre);
	public abstract String obtenerVideoPostulacion(String nickname, String oferta);
}
