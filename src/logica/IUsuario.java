package logica;

import excepciones.OfertaLaboralRepetidaException;
import java.util.*;

public interface IUsuario {
	
	/**
	 * Registra una oferta laboral en el sistema
	 * @param empresa Nombre de la empresa
	 * @param tipoOL Nombre del tipo de publicacion de la oferta laboral
	 * @param datosOL Datos de la oferta laboral
	 * @param keywords Lista de las Keywords asociadas a la oferta laboral
	 * @return true si la oferta se pudo registrar y false si no
	 */
	public abstract void ingresarDatosOL(String empresa, String tipoOL, DTOfertaLaboral datosOL, LinkedList<String> keywords) throws OfertaLaboralRepetidaException;
}
