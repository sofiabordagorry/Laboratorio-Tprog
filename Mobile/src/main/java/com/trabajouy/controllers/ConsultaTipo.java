package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import publicar.NoHayTiposException_Exception;
import publicar.DtKeywordWS;
import publicar.DtTipo;

/**
 * Servlet implementation class ConsultaTipo
 */
public class ConsultaTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaTipo() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	//esto hay que cambiarlo en todos por el publicador (web services)
    	//Factory fac =Factory.getInstance();
    	//IOfertaLaboral iol = fac.getIOfertaLaboral();
    	//ManejadorTipo mti = ManejadorTipo.getInstancia();
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	String tipoConsultado = request.getParameter("tipo_consultado");
    	
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
		
		//DTKeyword[] keys = iol.getDTKeywords();
		//request.setAttribute("keywords", keys);
 	
    	if(tipoConsultado == null) {
    		String[] types = null;
    		try {
    			
				types = port.listarNomTipos();
				request.setAttribute("systemTypes", types);
				request.setAttribute("NoTypesInSystem_Error", false);
	    		request.getRequestDispatcher("/WEB-INF/desktop/listar/listarTipos.jsp").forward(request, response);
			} catch (NoHayTiposException_Exception e) {
				request.setAttribute("NoTypesInSystem_Error", true);
	    		request.getRequestDispatcher("/WEB-INF/desktop/listar/listarTipos.jsp").forward(request, response);
			}
    		
    	}else {
    		//DTTipo typeData = mti.buscarTipo(tipoConsultado).getDataTipo();
    		DtTipo typeData = port.buscarTipo(tipoConsultado);
    		request.setAttribute("typeData", typeData);
			request.setAttribute("NoTypesInSystem_Error", false);
    		request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultaTipo.jsp").forward(request, response);
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
