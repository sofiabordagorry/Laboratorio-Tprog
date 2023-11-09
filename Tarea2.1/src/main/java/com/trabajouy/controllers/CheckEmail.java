package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CheckEmail
 */
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailToCheck = request.getParameter("correo");
        
        boolean isMailInUse = checkIfMailInUse(mailToCheck);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (isMailInUse) {
            out.print("Ya existe una cuenta asociada a ese correo");
        } else {
            out.print("Correo disponible");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean checkIfMailInUse(String mail) {
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		Boolean existeUsuario = port.existeMail(mail);
		return existeUsuario;
    }

}
