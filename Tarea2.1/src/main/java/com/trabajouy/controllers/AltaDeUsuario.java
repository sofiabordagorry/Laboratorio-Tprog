package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import publicar.DtEmpresa;
import publicar.DtPostulante;
import publicar.ExisteUnUsuarioYaRegistradoException_Exception;
import publicar.DtUsuario;

/**
 * Servlet implementation class AltaDeUsuario
 */
@MultipartConfig
public class AltaDeUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public AltaDeUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/desktop/altas/altaDeUsuario.jsp").forward(request, response);
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		if (usr == null) {
		
//			Factory fac = Factory.getInstance();
//			IUsuario icu = fac.getIUsuario();
			
			publicar.WebServicesService service = new publicar.WebServicesService();
			publicar.WebServices port = service.getWebServicesPort();
			
			String nickname, correo, nombre, apellido, contrasenia, tipoUsuario;
			nickname = request.getParameter("nickname");
			correo = request.getParameter("correo");
			nombre = request.getParameter("nombre");
			apellido = request.getParameter("apellido");
			contrasenia = request.getParameter("contrasenia");
			tipoUsuario = request.getParameter("tipoUsuario");
			//Part filePart = request.getPart("imagen");
			//InputStream fileContent = filePart.getInputStream();
			//byte[] imageData = IOUtils.toByteArray(fileContent);
			boolean seCumpleExcepcion = false;

			
			//Si es tipo empresa pido los datos de la empresa
			if ("empresa".equals(tipoUsuario)) {
				String descripcion, link;
				descripcion = request.getParameter("descripcion");
				link = request.getParameter("link");
				DtEmpresa dtemp = port.crearDTEmpresa(nickname, nombre, apellido, correo, contrasenia, descripcion, link, new byte[0]);
				try {
					//Ingreso los datos de la empresa
					port.ingresarDatosEmpresa(dtemp);
				} catch (ExisteUnUsuarioYaRegistradoException_Exception e) {
					DtUsuario dtusr = dtemp;
					request.setAttribute("datosIngresados", dtusr);
					request.setAttribute("tipoUsuario", tipoUsuario);
					if (e.getMessage().equals("Existe un usuario ya registrado con ese mail")) {
						request.setAttribute("emailError", e.getMessage());
					} else {
						request.setAttribute("nicknameError", e.getMessage());
					}
					seCumpleExcepcion = true;
				}
				
			//Si es tipo postulante pido los datos del postulante
			} else if ("postulante".equals(tipoUsuario)) {
				String nacionalidad, dateString;
				nacionalidad = request.getParameter("nacionalidad");
				dateString = request.getParameter("fechaNacimiento");
				DtPostulante dtpost = port.crearDTPostulante(nickname, nombre, apellido, correo, contrasenia, nacionalidad, dateString, new byte[0]);
				try {
					//Ingreso los datos del postulante
					port.ingresarDatosPostulante(dtpost);
				} catch (ExisteUnUsuarioYaRegistradoException_Exception e) {
					DtUsuario dtusr = dtpost;
					request.setAttribute("datosIngresados", dtusr);
					request.setAttribute("tipoUsuario", tipoUsuario);
					if (e.getMessage().equals("Existe un usuario ya registrado con ese mail")) {
						request.setAttribute("emailError", e.getMessage());
					} else {
						request.setAttribute("nicknameError", e.getMessage());
					}
					seCumpleExcepcion = true;
				}
			}
			
			if (seCumpleExcepcion) {
				request.getRequestDispatcher("/WEB-INF/desktop/errores/altaDeUsuarioError.jsp").forward(request, response);
			} else {
				request.setAttribute("registroExitoso", true);
				request.getRequestDispatcher("/WEB-INF/desktop/altas/altaDeUsuario.jsp").forward(request, response);
			}
		} else {
			response.sendError(403);
		}
	}
}
