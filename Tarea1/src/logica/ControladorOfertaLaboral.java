package logica;

import java.util.Date;
import java.util.Map;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.TipoRepetidoException;
import java.time.LocalDate;
import java.time.ZoneId;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
		
	}
	public void ingresarTipo(String nombre, String descripcion, int exposicionT, int duracion, Float costo, Date fechaAlta) throws TipoRepetidoException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
        Tipo t = mt.buscarTipo(nombre);
        if (t != null)
            throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        t = new Tipo(nombre, descripcion, exposicionT, duracion, costo, fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        mt.agregarTipo(t);
		}
	
	public void ingresarKeyword(String nombre){
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
        //Keyword k = mol.buscarKeyword(nombre);
        //if (t != null)
        //    throw new TipoRepetidoException("El Tipo " + nombre + " ya esta registrado");

        Keyword k = new Keyword(nombre);
        mol.agregarKeyword(k);
		}
	
	public String[] listarNomPaquetes() throws NoHayPaquetesException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Map<String, Paquete> mapPaquetes = mt.getPaquetes();
		if(mapPaquetes.size() == 0)
			throw new NoHayPaquetesException("No hay paquetes registrados");
		
		String[] dp = new String[mapPaquetes.size()];
		int i =0;
		for (Map.Entry<String, Paquete> entry : mapPaquetes.entrySet()) {
			dp[i] = entry.getKey();
			i++;
		}
		return dp;
	}
	
	public String[] listarNomTipos() throws NoHayTiposException{
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Map<String, Tipo> mapTipos = mt.getTipos();
		if(mapTipos.size() == 0)
			throw new NoHayTiposException("No hay tipos registrados");
		
		String[] dt = new String[mapTipos.size()];
		int i =0;
		for (Map.Entry<String, Tipo> entry : mapTipos.entrySet()) {
			dt[i] = entry.getKey();
			i++;
		}
		return dt;
	}
	
		
}
