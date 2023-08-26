package logica;

import java.time.LocalDate;
import java.util.Map;

public interface IUsuario {

	public abstract DTUsuario[] listarUsuarios();
	public DTUsuario mostrarInformacionUsuario(String nickname);
	public DTEmpresa[] listarEmpresas();
	public DTOfertaLaboral[] listarOfertasLaborales(String nomEmpresa);
	public DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral);
	public void modificarEmpresa(String nick, String nom,String ap, String nomE, String desc, String l);
	public void modificarPostulante(String nick, String nom, String ap, LocalDate f, String nac);
}
