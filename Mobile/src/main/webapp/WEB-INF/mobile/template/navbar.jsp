<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtUsuario"%>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
	<a class="navbar-brand" href="Home">
		<img src="media/imagenes/logo.png" width="150" height="auto" />
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav ml-auto">
			<%
				DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
				if (usr != null) {
			%>
			
			<li class="nav-item">
				<p style="color: white"><%= usr.getNickname() %></p>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="ConsultaPostulacion"><i class="fa-solid fa-square-poll-vertical"></i>Mis Postulaciones</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="CerrarSession"><i class="fa-solid fa-right-from-bracket fa-flip-horizontal"></i> Cerrar Sesión</a>
			</li>
			<%
				}
			%>
		</ul>
	</div>
</nav>