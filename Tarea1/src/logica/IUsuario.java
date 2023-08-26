package logica;

import java.time.LocalDate;
import java.util.Map;

import excepciones.EmpresasNoExistenException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.UsuariosNoExistenException;

public interface IUsuario {

	public abstract DTUsuario[] listarUsuarios() throws UsuariosNoExistenException;
	public DTUsuario mostrarInformacionUsuario(String nickname);
	public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException, NoExistenEmpresasOfertasLaboralesException;
	public DTOfertaLaboral[] listarOfertasLaborales(String nomEmpresa) throws OfertasLaboralesNoExistenException;
	public void modificarEmpresa(String nick, String nom,String ap, String nomE, String desc, String l);
	public void modificarPostulante(String nick, String nom, String ap, LocalDate f, String nac);
}
