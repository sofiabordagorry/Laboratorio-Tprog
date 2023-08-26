package logica;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.EmpresasNoExistenException;

public interface IUsuario {
	public abstract void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException;
	public abstract void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException;
	public abstract DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException;
}

