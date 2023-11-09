package logica;


import java.time.LocalDate;
import java.util.List;

import excepciones.EmpresaSinOfertasException;
import excepciones.PostulantesNoExistenException;
import excepciones.UsuarioSinPostulacionesException;
import excepciones.YaSePostuloException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.UsuariosNoExistenException;
import excepciones.ExisteUnUsuarioYaRegistradoException;

import excepciones.EmpresasNoExistenException;


public interface IUsuario {
	public abstract boolean existeMail(String mail);
	public abstract void modificarEmpresa(String nick, String nom, String ape, String desc, String link);
	public abstract void modificarPostulante(String nick, String nom, String ape, LocalDate fecha, String nac);
	public abstract DTOfertaLaboral[] listarOfertasLaborales(String nomEmpresa) throws OfertasLaboralesNoExistenException;
	public abstract DTUsuario mostrarInformacionUsuario(String nickname);
	public abstract DTUsuario[] listarUsuarios() throws UsuariosNoExistenException;
	public abstract void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException;
	public abstract void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException;
	public abstract DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException, NoExistenEmpresasOfertasLaboralesException;
	public abstract DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException;
	public abstract DTPostulante[] listarPostulantes() throws PostulantesNoExistenException;
	public abstract DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException;
	public abstract DTOfertaLaboral[] listarOfertasLaboralesIngresadas(String empresa) throws EmpresaSinOfertasException;
	public abstract void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante, String video) throws YaSePostuloException;
	public abstract DTOfertaLaboral[] listarOfertasPostulado(String nickname) throws UsuarioSinPostulacionesException;
	public abstract DTEmpresa buscarEmpresa(String nickname);
	public abstract DTUsuario buscarDTUsuario(String login);
	public abstract DTUsuario buscarDTUsuarioPorMail(String login);
	public abstract boolean verificacionDePostulantePostulacion(String nicknameConsultado, String nombreOferta, String nombreEmp);
	public abstract DTPostulante dataPostulante(String nickname);
	public abstract boolean verificacionCompraPaq(String emp, String paq);
	public abstract boolean estaSiguiendo(String seguidor, String seguido);
	public abstract void dejarSeguimiento(String seguidor, String seguido);
	public abstract void seguirUsuario(String seguidor, String seguido);
	public abstract DTPostulante crearDTPostulante(String nickname, String nom, String apellido, String correo, String contrasenia, String nacionalidad, String fechaDeNacimiento, byte[] image);
	public abstract DTEmpresa crearDTEmpresa(String nickname, String nom, String apellido, String correo, String contrasenia, String descripcion, String link, byte[] image);
	public abstract String[] obtenerSeguidores(String nickname);
	public abstract String[] obtenerSeguidos(String nickname);
	public abstract boolean esFavorito(String nickname, String oferta);
	public abstract void quitarOfertaFav(String nickname, String oferta);
	public abstract void agregarOfertaFav(String nickname, String oferta);
	public abstract boolean existePostulante(String nickname);
	public abstract boolean existeEmpresa(String nickname);
}






