package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import excepciones.OfertasLaboralesNoExistenNingunaException;
import logica.*;

@WebServlet ("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void init(HttpServletRequest request) {
    	HttpSession misesion = request.getSession();
    	if (misesion.getAttribute("loginEstado") == null) {
    		misesion.setAttribute("loginEstado", LoginEstado.NO_LOGIN);
    		misesion.setAttribute("filtro", null);
    		
    		Factory fac = Factory.getInstance();
    		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
    		
    		/*IOfertaLaboral iol = fac.getIOfertaLaboral();
    		try {
    			DTOfertaLaboral dtols[] = iol.listarTodasOfertasLaborales();
    			request.getSession().setAttribute("listaOfertasLaborales", dtols);
    		} catch (OfertasLaboralesNoExistenNingunaException e) {
    			e.printStackTrace();
    		}*/
    		
    		Keyword[] keys = mol.getKeywords();
    		request.getSession().setAttribute("keywords", keys);
    	}
    	misesion.setAttribute("filterType", "AllOffers");
    	Factory fac = Factory.getInstance();
		
		IOfertaLaboral iol = fac.getIOfertaLaboral();
		try {
			DTOfertaLaboral dtols[] = iol.listarTodasOfertasLaborales();
			request.getSession().setAttribute("listaOfertasLaborales", dtols);
		} catch (OfertasLaboralesNoExistenNingunaException e) {
			e.printStackTrace();
		}
    }
    
    public static LoginEstado getLoginEstado(HttpServletRequest request) {
    	return (LoginEstado) request.getSession().getAttribute("loginEstado");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	init(request);
  
    	switch (getLoginEstado(request)) {
    		case NO_LOGIN:
    			request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);
    		case LOGIN_INCORRECTO:
    			break;
    		case LOGIN_CORRECTO:
    			request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);;
    	}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
