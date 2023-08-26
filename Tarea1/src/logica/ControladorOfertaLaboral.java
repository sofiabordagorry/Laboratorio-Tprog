package logica;

import excepciones.NoExistenPaquetesException;

public class ControladorOfertaLaboral implements IOfertaLaboral {
	public ControladorOfertaLaboral() {
	}
	
	public DTPaquete[] listarPaquetes() {
		ManejadorTipo mt = ManejadorTipo.getInstancia();
		Paquete[] paquetes = mt.getPaquetes();
		
		if (paquetes != null) {
			DTPaquete[] dt = new DTPaquete[paquetes.length];
			Paquete paq;
			
			for(int i = 0; i <paquetes.length; i++) {
				paq = paquetes[i];
				dt[i] = paq.getDataPaquete();
			}
			
			return dt;
		} else {
			return null;
		}
	}
	
    public String DatosPaqueteAMostrar(DTPaquete p) {
    	return "Nombre: " + p.getNombre() + "\nDescripcion: " + p.getDescripcion() + "\nPeriodo de validez: " + p.getPeriodoDeValidez() + "\nDescuento: " + p.getDescuento() + "\nCosto: " + p.getCostoAsociado();
    }

}
