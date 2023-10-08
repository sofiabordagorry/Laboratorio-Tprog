package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import excepciones.UsuarioSinPostulacionesException;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorUsuario;
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
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	Usuario usr = (Usuario) request.getSession().getAttribute("usuario_logueado");
    	String nombreOferta = request.getParameter("nombreOfertaConsultada");
    	request.setAttribute("nombreOferta", nombreOferta);
    	
    	Factory fac = Factory.getInstance();
		IUsuario cusr = fac.getIUsuario();
		IOfertaLaboral col = fac.getIOfertaLaboral();
    	
		String nicknameConsultado = request.getParameter("postulanteConsultado");
		
		System.out.println(nicknameConsultado + " " + nombreOferta);
		String nicknameEnSesion = null;
		if (usr != null) {
			nicknameEnSesion = usr.getNickname();
		} else {
			response.sendError(403);
		}
		
		if (nicknameConsultado == null) {
			try {
				DTOfertaLaboral[] postulaciones = cusr.listarOfertasPostulado(nicknameEnSesion);
				request.setAttribute("misPostulaciones", postulaciones);
			} catch (UsuarioSinPostulacionesException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/listar/listarPostulaciones.jsp").forward(request, response);
			
		} else { 
			DTPostulacion dataPostulacion = col.dataPostulacion(nicknameConsultado, nombreOferta);
			ManejadorUsuario musr = ManejadorUsuario.getInstancia();
			request.setAttribute("usuarioPostulacion", musr.buscarPostulante(nicknameConsultado).getDataPostulante());
			request.setAttribute("dataPostulacion", dataPostulacion);
			request.getRequestDispatcher("/WEB-INF/consultas/consultaPostulacion.jsp").forward(request, response);
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