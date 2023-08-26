package logica;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.EmpresasNoExistenException;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.EmpresaSinOfertasException;
import excepciones.PostulantesNoExistenException;
import excepciones.YaSePostuloException;


public interface IUsuario {
	public abstract void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException;
	public abstract void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException;
	public abstract DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException;
	public abstract DTPostulante[] listarPostulantes() throws PostulantesNoExistenException;
	public abstract DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException;
	public abstract void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException;
}

