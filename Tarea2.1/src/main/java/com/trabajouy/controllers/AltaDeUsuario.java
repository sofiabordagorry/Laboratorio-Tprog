package com.trabajouy.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.trabajouy.exceptions.ExisteUnUsuarioYaRegistradoException;
import com.trabajouy.model.DTEmpresa;
import com.trabajouy.model.DTPostulante;
import com.trabajouy.model.DTUsuario;
import com.trabajouy.model.Empresa;
import com.trabajouy.model.Factory;
import com.trabajouy.model.IUsuario;
import com.trabajouy.model.ManejadorUsuario;
import com.trabajouy.model.Usuario;
import com.trabajouy.model.Postulante;

/**
 * Servlet implementation class AltaDeUsuario
 */
public class AltaDeUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AltaDeUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/altas/altaDeUsuario.jsp").forward(request, response);
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Factory fac = Factory.getInstance();
		IUsuario icu = fac.getIUsuario();
		
		String nickname, correo, nombre, apellido, contrasenia, tipoUsuario;
		nickname = request.getParameter("nickname");
		correo = request.getParameter("correo");
		nombre = request.getParameter("nombre");
		apellido = request.getParameter("apellido");
		contrasenia = request.getParameter("contrasenia");
		tipoUsuario = request.getParameter("tipoUsuario");
		/*Part filePart = request.getPart("imagen");
		byte[] imagen = this.deImgAByte(filePart);*/
		System.out.println("Datos ingresados: " + nickname + " " + correo + " " + nombre + " " + apellido + " " + contrasenia + " " + tipoUsuario);
		boolean seCumpleExcepcion = false;
		
		//Si es tipo empresa pido los datos de la empresa
		if("empresa".equals(tipoUsuario)) {
			String descripcion, link;
			descripcion = request.getParameter("descripcion");
			link = request.getParameter("link");
			DTEmpresa dtemp = new DTEmpresa(nickname, nombre, apellido, correo, null, descripcion, link, contrasenia, new byte[0]);
			
			try {
				//Ingreso los datos de la empresa
				icu.ingresarDatosEmpresa(dtemp);
			} catch(ExisteUnUsuarioYaRegistradoException e) {
				DTUsuario dtusr = dtemp;
				request.setAttribute("datosIngresados", dtusr);
				request.setAttribute("tipoUsuario", tipoUsuario);
				if(e.getMessage().equals("Existe un usuario ya registrado con ese mail")) {
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según el formato de tu input date
			LocalDate fechaNacimiento = LocalDate.parse(dateString, formatter);
			DTPostulante dtpost = new DTPostulante(nickname, nombre, apellido, correo, fechaNacimiento, nacionalidad, contrasenia, new byte[0]);
			
			try {
				//Ingreso los datos del postulante
				icu.ingresarDatosPostulante(dtpost);
			} catch(ExisteUnUsuarioYaRegistradoException e) {
				DTUsuario dtusr = dtpost;
				request.setAttribute("datosIngresados", dtusr);
				request.setAttribute("tipoUsuario", tipoUsuario);
				if(e.getMessage().equals("Existe un usuario ya registrado con ese mail")) {
					request.setAttribute("emailError", e.getMessage());
				} else {
					request.setAttribute("nicknameError", e.getMessage());
				}
				seCumpleExcepcion = true;
			}
		}
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Postulante[] post = mu.getPostulantes();
		Empresa[] emp = mu.getEmpresas();
		
		if(post != null) {
			System.out.println("Cantidad de postulantes: " + post.length);
		}
		
		if(emp != null) {
			System.out.println("Cantidad de empresas: " + emp.length);
		}
		
		if(seCumpleExcepcion) {
			request.getRequestDispatcher("/WEB-INF/errores/altaDeUsuarioError.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/altas/altaDeUsuario.jsp").forward(request, response);
		}
	}

	/*private byte[] deImgAByte(Part filePart) throws IOException {
		if(filePart != null && filePart.getSize() > 0) {
			// Convierte el InputStream de la parte del archivo a un arreglo de bytes
		    InputStream inputStream = filePart.getInputStream();
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int bytesRead;
		    
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outputStream.write(buffer, 0, bytesRead);
		    }
		    
		    byte[] imageBytes = outputStream.toByteArray();
		    
		    // Ahora 'imageBytes' contiene los bytes de la imagen cargada
		    // Puedes guardar 'imageBytes' en tu sistema o realizar otras operaciones con él

		    // Cierra los flujos
		    outputStream.close();
		    inputStream.close();
		    return imageBytes;
		} else {
			return new byte[0];
		}
	}*/
}
