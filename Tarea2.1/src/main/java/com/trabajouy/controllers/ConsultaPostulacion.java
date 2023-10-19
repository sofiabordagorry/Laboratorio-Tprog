package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import excepciones.UsuarioSinPostulacionesException;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.Empresa;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorUsuario;
import logica.Postulacion;
import logica.Postulante;
import logica.Usuario;

/**
 * Servlet implementation class ConsultaPostulacion
 */
public class ConsultaPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaPostulacion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean verificacionDePostulantePostulacion(String nicknameConsultado, String nombreOferta, String nombreEmp) {
    	ManejadorUsuario musr = ManejadorUsuario.getInstancia();
    	Postulante usr = musr.buscarPostulante(nicknameConsultado);
    	List<Postulacion> postulaciones = usr.getPostulaciones();
    	boolean correcto = false;
    	for(Postulacion post : postulaciones) {
    		if(post.getOfertaLaboral().getNombre().equals(nombreOferta) && 
    				post.getOfertaLaboral().getEmpresaCreadora().equals(nombreEmp))
    			correcto = true;
    	}
    	
    	return correcto;
    }
  
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	Usuario usr = (Usuario) request.getSession().getAttribute("usuario_logueado");
    	String nombreOferta = request.getParameter("nombreOfertaConsultada");
    	request.setAttribute("nombreOferta", nombreOferta);
    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
    	
    	Factory fac = Factory.getInstance();
		IUsuario cusr = fac.getIUsuario();
		IOfertaLaboral col = fac.getIOfertaLaboral();
    	
		String nicknameConsultado = request.getParameter("postulanteConsultado");
	
		String nicknameEnSesion = null;
		if (usr != null) {
			nicknameEnSesion = usr.getNickname();
			if (nicknameConsultado == null && usr instanceof Postulante) {
				try {
					DTOfertaLaboral[] postulaciones = cusr.listarOfertasPostulado(nicknameEnSesion);
					request.setAttribute("misPostulaciones", postulaciones);
				} catch (UsuarioSinPostulacionesException e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("/WEB-INF/listar/listarPostulaciones.jsp").forward(request, response);
				
			} else if(usr instanceof Postulante && nicknameConsultado.equals(usr.getNickname()) || 
						usr instanceof Empresa && this.verificacionDePostulantePostulacion(nicknameConsultado, nombreOferta, usr.getNickname())){ 
				DTPostulacion dataPostulacion = col.dataPostulacion(nicknameConsultado, nombreOferta);
				ManejadorUsuario musr = ManejadorUsuario.getInstancia();
				request.setAttribute("usuarioPostulacion", musr.buscarPostulante(nicknameConsultado).getDataPostulante());
				request.setAttribute("dataPostulacion", dataPostulacion);
				request.getRequestDispatcher("/WEB-INF/consultas/consultaPostulacion.jsp").forward(request, response);
			} else {
				response.sendError(403);
			}
		} else {
			response.sendError(403);
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}

}