package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.DTUsuario;
import publicar.DtEmpresa;
import publicar.DtOfertaLaboral;
import publicar.DtOfertaLaboralWS;
import publicar.DtUsuario;
import publicar.DtUsuarioWS;
import publicar.OfertasLaboralesNoExistenNingunaException_Exception;
import publicar.UsuariosNoExistenException_Exception;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class RealizarBusqueda
 */
public class RealizarBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarBusqueda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		String busqueda = request.getParameter("texto-busqueda");
		
		DtUsuarioWS usuarios = null;
		try {
			usuarios = port.listarUsuarios();
			List<DtUsuario> users = usuarios.getUsers();
			List<DtUsuario> usuariosFiltrada = new ArrayList<DtUsuario>();
			for (DtUsuario user: users) {
				if (user instanceof DtEmpresa) {
					if ((user.getNickname().contains(busqueda) || user.getNombre().contains(busqueda)) || ((DtEmpresa) user).getDescripcion().contains(busqueda)) {
						usuariosFiltrada.add(user);
					}
				}
			}
			request.setAttribute("usuariosFiltrados", usuariosFiltrada);
		} catch (UsuariosNoExistenException_Exception e) {
			e.printStackTrace();
		}
		
		DtOfertaLaboralWS ofertas = null;
		try {
			ofertas = port.getDTOfertasLaborales();
			List<DtOfertaLaboral> ofs = ofertas.getOfs();
			List<DtOfertaLaboral> ofsFiltrada = new ArrayList<DtOfertaLaboral>();
			for (DtOfertaLaboral of: ofs) {
				if (of.getNombre().contains(busqueda) || of.getDescripcion().contains(busqueda)) {
					ofsFiltrada.add(of);
				}
			}
			request.setAttribute("ofertasFiltradas", ofsFiltrada);
		} catch (OfertasLaboralesNoExistenNingunaException_Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/desktop/listar/listarBusqueda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
