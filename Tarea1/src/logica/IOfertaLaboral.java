package logica;

import java.util.Date;
import excepciones.TipoRepetidoException;
import excepciones.NoHayTiposException;
import excepciones.NoHayPaquetesException;
import excepciones.TipoYaAgragadoException;

public interface IOfertaLaboral {

	public abstract void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException;
	
	public abstract void ingresarKeyword(String nombre);
	
	public abstract String[] listarNomPaquetes() throws NoHayPaquetesException;
	
	public abstract String[] listarNomTipos() throws NoHayTiposException;
	
	public abstract void agregarTipoAPaquete(int cantidad, String tipPaquete, String tipTipo) throws TipoYaAgragadoException;
}
