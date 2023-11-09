package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import publicar.DtKeywordWS;
import publicar.DtOfertaLaboralWS;
import publicar.LoginEstado;
import publicar.OfertasLaboralesNoExistenNingunaException_Exception;

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
    	}
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	
    	// Factory fac = Factory.getInstance();
    	// IOfertaLaboral iol = fac.getIOfertaLaboral();
    	
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
    	request.getSession().setAttribute("filterType", "AllOffers");
		
		try {
			DtOfertaLaboralWS dtols = port.getDTOfertasLaborales();
			request.getSession().setAttribute("listaOfertasLaborales", dtols.getOfs());
		} catch (OfertasLaboralesNoExistenNingunaException_Exception e) {
			e.printStackTrace();
		}
    }
    
    public static LoginEstado getLoginEstado(HttpServletRequest request) {
    	return (LoginEstado) request.getSession().getAttribute("loginEstado");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	init(request);
	    // Es un dispositivo de escritorio
		switch (getLoginEstado(request)) {
		case NO_LOGIN:
			request.getRequestDispatcher("/WEB-INF/desktop/home/login.jsp").forward(request, response);
		case LOGIN_INCORRECTO:
			break;
		case LOGIN_CORRECTO:
			request.getRequestDispatcher("/WEB-INF/desktop/home/login.jsp").forward(request, response);;
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
