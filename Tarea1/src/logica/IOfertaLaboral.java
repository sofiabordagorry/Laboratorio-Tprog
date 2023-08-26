package logica;

import excepciones.TipoPubNoExistenException;
import excepciones.KeywordsNoExistenException;
import excepciones.OfertaLaboralRepetidaException;

public interface IOfertaLaboral {
	public abstract DTTipo[] listarTipoPublicacionOfertaLaboral() throws TipoPubNoExistenException;
	public abstract String[] listarKeywords() throws KeywordsNoExistenException;
	public abstract void ingresarDatosOL(String empresa, String nombreTipo, DTOfertaLaboral ol) throws OfertaLaboralRepetidaException;
}
