package logica;

import excepciones.TipoPubNoExistenException;
import excepciones.KeywordsNoExistenException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteRepetidoException;

public interface IOfertaLaboral {
	public abstract DTPaquete[] listarPaquetes();
	public abstract String DatosPaqueteAMostrar(DTPaquete p);
	public abstract DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException;
	public abstract String[] listarKeywords() throws KeywordsNoExistenException;
	public abstract void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ol) throws OfertaLaboralRepetidaException;
	public abstract void ingresarDatosPaquete(DTPaquete datosPaquete) throws PaqueteRepetidoException;

}
