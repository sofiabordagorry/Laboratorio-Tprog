package logica;

import excepciones.EmpresasNoExistenException;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
	public DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Empresa[] empresas = mu.getEmpresas();
		
		if (empresas != null) {
			DTEmpresa[] de = new DTEmpresa[empresas.length];
			Empresa empresa;
			
			for(int i = 0; i < empresas.length; i++) {
				empresa = empresas[i];
				de[i] = empresa.getDataEmpresaALO();
			}
			
			return de;
		} else 
			throw new EmpresasNoExistenException("No existen Empresas registradas");
	}
}
