package com.trabajouy.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import logica.*;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				req.getRequestDispatcher("/WEB-INF/home/login.jsp").
				forward(req, resp);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession objSesion = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		LoginEstado nuevoEstado = LoginEstado.NO_LOGIN;
		RequestDispatcher dispatcher;
		
		/*System.out.println(login);
		System.out.println(password);
		
		Postulante testPost = new Postulante("nickname","nombre", "apellido", "correo", LocalDate.now(), "nacionalidad", "password");
		ManejadorUsuario.getInstancia().agregarPostulante(testPost);*/

		// chequea contraseña
		Usuario usr = ManejadorUsuario.getInstancia().buscarUsuario(login);
		if (usr == null) {
			usr = ManejadorUsuario.getInstancia().buscarUsuarioPorMail(login);
		}
		//System.out.println(usr);
		
		
		if (usr != null && !usr.getContrasenia().equals(password)) {
			nuevoEstado = LoginEstado.LOGIN_INCORRECTO;
			dispatcher = request.getRequestDispatcher("/WEB-INF/home/login.jsp");
		} else if (usr != null && usr.getContrasenia().equals(password)) {
			nuevoEstado = LoginEstado.LOGIN_CORRECTO;
			// setea el usuario logueado
			request.getSession().setAttribute("usuario_logueado", usr);
			dispatcher = request.getRequestDispatcher("/WEB-INF/template/index.jsp");
		} else {
			nuevoEstado = LoginEstado.LOGIN_INCORRECTO;
			dispatcher = request.getRequestDispatcher("/WEB-INF/home/login.jsp");
		}

		objSesion.setAttribute("estado_sesion", nuevoEstado);
		dispatcher.forward(request, response);
	}
}
