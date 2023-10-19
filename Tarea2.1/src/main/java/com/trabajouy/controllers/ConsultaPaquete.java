package com.trabajouy.controllers;

import java.io.IOException;

import excepciones.NoHayPaquetesException;
import logica.DTPaquete;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;
import logica.Paquete;
import logica.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class ConsultaUsuario
 */
public class ConsultaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultaPaquete() {
    	super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	// Obtén el nickname del usuario que se consulta de alguna manera
    	ManejadorTipo mtip = ManejadorTipo.getInstancia();
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	String nombrePaq = request.getParameter("paqueteConsultado");
    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
    	
    	request.setAttribute("paqComprado", false);
    	request.setAttribute("esEmpresa", false);
     	
     	HttpSession session = request.getSession();
     	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
     	String nicknameEnSesion = null;
     	ManejadorUsuario murs = ManejadorUsuario.getInstancia();
     	if(user != null) {
     		nicknameEnSesion = user.getNickname();
     		if (murs.existeEmpresa(nicknameEnSesion)) {
 	            // Es una empresa, puede comprar
     			request.setAttribute("esEmpresa", true);
 	       }
     
     	}
    	
    	if (nombrePaq == null) {
    	    // No se especificó un paquete para consultar, mostrar la página ListarPaquetes
    		try {
				String[] paqs = col.listarNomPaquetes();
	    		request.setAttribute("paquetes", paqs);
	    		request.setAttribute("hayPaqs", true);
			} catch (NoHayPaquetesException e) {
	    		request.setAttribute("hayPaqs", false);
			}

    	    request.getRequestDispatcher("/WEB-INF/listar/listarPaquetes.jsp").forward(request, response);
    	} else {
	        Paquete paq = mtip.buscarPaquete(nombrePaq);
	        DTPaquete paquete = paq.getDataPaquete();
	        request.setAttribute("dataPaquete", paquete);
	        request.getRequestDispatcher("/WEB-INF/consultas/consultaPaquete.jsp").forward(request, response);
    	    
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