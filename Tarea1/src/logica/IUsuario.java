package logica;

import excepciones.ExisteUnUsuarioYaRegistradoException;

public interface IUsuario {
	public abstract void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException;
	public abstract void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException;;
}
