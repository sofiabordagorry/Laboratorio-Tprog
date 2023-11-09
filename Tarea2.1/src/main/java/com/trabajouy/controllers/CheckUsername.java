package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CheckUsername
 */
public class CheckUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameToCheck = request.getParameter("username");
        
        boolean isUsernameInUse = checkIfUsernameInUse(usernameToCheck);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (isUsernameInUse) {
            out.print("Nombre de usuario en uso");
        } else {
            out.print("Nombre de usuario disponible");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean checkIfUsernameInUse(String username) {
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		Boolean existeUsuario = port.existeEmpresa(username);
		if (existeUsuario == false) {
			existeUsuario = port.existePostulante(username);
		}
		return existeUsuario;
    }

}
