package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtUsuario;

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
    	DtUsuario usrLogueado = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
    	
//    	Factory fac = Factory.getInstance();
//    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	
    	publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		DtKeywordWS keys = port.getDTKeyword();
		List<DtKeyword> dtkeys = keys.getKeys();
		request.setAttribute("keywords", dtkeys);
		
    	if (usrLogueado != null) {
    		request.getSession().setAttribute("usuario_logueado", null);
    		request.getSession().setAttribute("estado_sesion", null);
    		request.getSession().setAttribute("filterType", "AllOffers");
    		request.getRequestDispatcher("/WEB-INF/desktop/home/login.jsp").forward(request, response);
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
