package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import logica.Usuario;
import logica.DTOfertaLaboral;
import logica.ManejadorOfertaLaboral;

/**
 * Servlet implementation class consultaOferta
 */
public class ConsultaOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
    	String ofertaConsultada = (String) request.getParameter("oferta_consultada");
    	System.out.println(ofertaConsultada);
    	DTOfertaLaboral oferta = ManejadorOfertaLaboral.getInstance().buscarOfertaLaboral(ofertaConsultada).getDataOfertaLaboral();
    	
    	 // Set job offer as an attribute to pass it to the JSP.
        request.setAttribute("oferta_laboral", oferta);

        // Forward the request to the JSP for rendering.
        request.getRequestDispatcher("/WEB-INF/consultas/consultarOferta.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}