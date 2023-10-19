<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="logica.Usuario"%>
<%@ page import="logica.Empresa"%>
<%@ page import="logica.Keyword"%>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="java.io.IOException" %>
<div class="col col-md-3 col-sm-12" id="sidebar">
	<div class="list-group m-2">
		<a href="#">
			<div class="header d-flex" data-toggle="collapse" aria-controls="actionList" href="#actionList">
				
				<%
					Usuario usr = (Usuario) request.getSession().getAttribute("usuario_logueado");
					if(usr == null) {
				%>
				<h2 class="list-group-item-heading mr-auto p-2">Area de Visitante</h2>
				<%
					} else {
				%>
				<h2 class="list-group-item-heading mr-auto p-2">Area de Usuario</h2>
				<%
					}
				%>
				
          		<img class="p-2" width="50" height="50" src="media/imagenes/collapse.png" />
        	</div>
		</a>
		<div id="actionList" class="collapse">
			<%
				if(usr == null) {
			%>
			<a class="list-group-item list-group-item-action" href="AltaDeUsuario"><i class="fa-solid fa-circle-user"></i> <b>Registrarse</b></a>
			<%
				} else {
			%>
			<a class="list-group-item list-group-item-action" href="ConsultaUsuario?usuarioConsultado=<%=usr.getNickname()%>"><i class="fa-solid fa-circle-user"></i> Mi Perfil</a>
			<%	
					if(usr instanceof Empresa){
			%>
			<a class="list-group-item list-group-item-action" href="AltaDeOfertaLaboral"><i class="fa-solid fa-users"></i> Publicar Oferta Laboral</a>
			<a class="list-group-item list-group-item-action" href="Ofertas?filterType=MyOffers"><i class="fa-solid fa-square-poll-vertical"></i> Mis Ofertas</a>
			<%
					} else {
			%>
			<a class="list-group-item list-group-item-action" href="ConsultaPostulacion"><i class="fa-solid fa-square-poll-vertical"></i> Mis Postulaciones</a>
			<%
					}
				}
			%>
			<a class="list-group-item list-group-item-action" href="ConsultaUsuario"><i class="fa-solid fa-users"></i> Busqueda de usuarios</a>
			<a class="list-group-item list-group-item-action" href="ConsultaTipo"><i class="fa-solid fa-square-poll-vertical"></i> Consultar tipos</a>
			<a class="list-group-item list-group-item-action" href="ConsultaPaquete"><i class="fa-solid fa-box-open"></i> Consultar paquetes</a>
		</div>
	</div>
	<div class="list-group m-2">
		<a href="#">
			<div class="header d-flex" data-toggle="collapse" aria-controls="keywordList" href="#keywordList">
				<h2 class="list-group-item-heading mr-auto p-2">Keywords</h2>
				<img class="p-2" width="50" height="50" src="media/imagenes/collapse.png" />
			</div>
		</a>
		<div id="keywordList" class="collapse">
			<%
				Keyword[] keys = (Keyword[]) request.getAttribute("keywords");
				for(int i = 0; i < keys.length; i++) {
			%>
			<a class="list-group-item list-group-item-action" href="Ofertas?filtro=<%= keys[i].getNombre() %>"><%= keys[i].getNombre() %></a>
			<%
				}
			%>
		</div>
	</div>
</div>
