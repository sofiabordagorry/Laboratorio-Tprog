package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.trabajouy.exceptions.NoHayTiposException;
import com.trabajouy.exceptions.TipoRepetidoException;
import com.trabajouy.model.DTTipo;
import com.trabajouy.model.Factory;
import com.trabajouy.model.IOfertaLaboral;
import com.trabajouy.model.ManejadorTipo;

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
    	Factory fac =Factory.getInstance();
    	IOfertaLaboral iol = fac.getIOfertaLaboral();
    	ManejadorTipo mti = ManejadorTipo.getInstancia();
    	String tipoConsultado = request.getParameter("tipo_consultado");
    	LocalDate fecha = LocalDate.now();
    	try {
			iol.ingresarTipo("nombre", "descripcion", 10, 3, 547.8f,Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} catch (TipoRepetidoException e) {
		}
    	
    	
    	if(tipoConsultado == null) {
    		String[] types = null;
    		try {
				types = iol.listarNomTipos();
				request.setAttribute("systemTypes", types);
				request.setAttribute("NoTypesInSystem_Error", false);
	    		request.getRequestDispatcher("/WEB-INF/listar/listarTipos.jsp").forward(request, response);
			} catch (NoHayTiposException e) {
				request.setAttribute("NoTypesInSystem_Error", true);
	    		request.getRequestDispatcher("/WEB-INF/listar/listarTipos.jsp").forward(request, response);
			}
    		
    	}else {
    		//DTTipo[] typesData = null;
    		DTTipo typeData = mti.buscarTipo(tipoConsultado).getDataTipo();
    		request.setAttribute("typeData", typeData);
			request.setAttribute("NoTypesInSystem_Error", false);
    		request.getRequestDispatcher("/WEB-INF/consultas/consultaTipo.jsp").forward(request, response);
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