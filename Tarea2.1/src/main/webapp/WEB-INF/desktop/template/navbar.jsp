<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtUsuario"%>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
	<a class="navbar-brand" href="Home">
		<img src="media/imagenes/logo.png" width="150" height="auto" />
	</a>
	<div class="ml-auto mr-auto custom-search">
        <!-- Barra de búsqueda -->
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Buscar">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
        </form>
    </div>
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
				<a class="nav-link" href="ConsultaUsuario?usuarioConsultado=<%=usr.getNickname()%>"><i class="fa-solid fa-user" style="color: #dfe2e7;"></i> <%= usr.getNickname() %></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="CerrarSession"><i class="fa-solid fa-right-from-bracket fa-flip-horizontal"></i> Cerrar Sesión</a>
			</li>
			 
			<%
				} else {
			%>
			
			<li class="nav-item">
				<a class="nav-link" href="Login"><i class="fa-solid fa-right-to-bracket" style="color: #dfe2e7;"></i> Iniciar sesión</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="AltaDeUsuario"><i class="fa-solid fa-user-plus" style="color: #dfe2e7;"></i> Registrar Usuario</a>
			</li>
			
			<%
				}
			%>
		</ul>
	</div>
</nav>