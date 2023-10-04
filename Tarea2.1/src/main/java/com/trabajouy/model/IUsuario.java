package com.trabajouy.model;


import java.time.LocalDate;

import com.trabajouy.exceptions.EmpresaSinOfertasException;
import com.trabajouy.exceptions.PostulantesNoExistenException;
import com.trabajouy.exceptions.YaSePostuloException;
import com.trabajouy.exceptions.NoExistenEmpresasOfertasLaboralesException;
import com.trabajouy.exceptions.OfertasLaboralesNoExistenException;
import com.trabajouy.exceptions.UsuariosNoExistenException;
import com.trabajouy.exceptions.ExisteUnUsuarioYaRegistradoException;

import com.trabajouy.exceptions.EmpresasNoExistenException;


public interface IUsuario {
	public abstract void modificarEmpresa(String nick, String nom,String ap, String desc, String l);
	public abstract void modificarPostulante(String nick, String nom, String ap, LocalDate f, String nac);
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
	public abstract void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException;
}





