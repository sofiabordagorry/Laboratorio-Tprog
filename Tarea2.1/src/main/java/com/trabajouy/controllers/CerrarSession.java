package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import logica.Keyword;
import logica.LoginEstado;
import logica.ManejadorOfertaLaboral;
import logica.Usuario;

/**
 * Servlet implementation class CerrarSession
 */
public class CerrarSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CerrarSession() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usrLogueado = (Usuario) request.getSession().getAttribute("usuario_logueado");
    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
    	if (usrLogueado != null) {
    		request.getSession().setAttribute("usuario_logueado", null);
    		request.getSession().setAttribute("estado_sesion", null);
    		request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);
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
