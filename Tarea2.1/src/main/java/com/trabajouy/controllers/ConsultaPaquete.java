package com.trabajouy.controllers;

import java.io.IOException;
import java.util.List;

import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtUsuario;
import publicar.DtEmpresa;
import publicar.DtPaquete;

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

		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		HttpSession session = request.getSession();
		DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
    	
    	String nombrePaq = request.getParameter("paqueteConsultado");
    	
		DtKeywordWS keyss = port.getDTKeyword();
		List<DtKeyword> key = keyss.getKeys();
		request.setAttribute("keywords", key);
    	
    	request.setAttribute("paqComprado", false);
    	request.setAttribute("esEmpresa", false);
     	
     	String nicknameEnSesion = null;
     	if(user != null) {
            nicknameEnSesion = user.getNickname();
            DtEmpresa empresa = port.buscarEmpresa(nicknameEnSesion);
            if (empresa.getNickname() != null) {
                // Es una empresa, puede comprar
                request.setAttribute("esEmpresa", true);
                if (nombrePaq != null && !port.verificacionCompraPaq(nicknameEnSesion, nombrePaq)) {
                    request.setAttribute("paqComprado", true);
                    request.setAttribute("compraExitosa", false);
                }

           }

        }
    	
    	if (nombrePaq == null) {
    	    // No se especificó un paquete para consultar, mostrar la página ListarPaquetes
    		String[] paqs = port.listarNomPaquetes();
    		if (paqs != null) {
	    		request.setAttribute("paquetes", paqs);
	    		request.setAttribute("hayPaqs", true);
			} else {
	    		request.setAttribute("hayPaqs", false);
			}

    	    request.getRequestDispatcher("/WEB-INF/desktop/listar/listarPaquetes.jsp").forward(request, response);
    	} else {
	        DtPaquete paquete = port.buscarPaquete(nombrePaq);
	        request.setAttribute("dataPaquete", paquete);
	        request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultaPaquete.jsp").forward(request, response);
    	    
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
