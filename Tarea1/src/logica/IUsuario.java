package logica;

import excepciones.EmpresasNoExistenException;

public interface IUsuario {
	public abstract DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException;
}
