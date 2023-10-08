<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.DTUsuario"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/stylesListar.css">
	    <title>Trabajo.uy</title>
	</head>
	<body>
	    <jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	            <jsp:include page="../template/sidebar.jsp" />
				<div class="col col-md-9 col-sm-12" id="main-content">
					<div id="listarContainer" class="form-row align-items-center justify-content-center contenedor">
						<% 
							boolean noUsers = (boolean) request.getAttribute("NoUsersInSystem_Error");
							if (!noUsers) {
								DTUsuario[] users = (DTUsuario[]) request.getAttribute("SystemUsers");
						%>
						<h1 class="text-center">Busqueda de Usuarios</h1>
						<div id="barraBusqueda" class="mx-auto">
							<div class="input-group mb-3">
	            				<input type="text" id="buscador" class="form-control" placeholder="Buscar nickname">
	       					</div>
						</div>
						<ul class="list-group" id="lista-elementos">
							<%
								for (int i = 0; i < users.length; i++) {
							%>
							<li class="list-group-item text-center"><a href="?usuarioConsultado=<%=users[i].getNickname()%>"><%=users[i].getNickname()%></a></li>
							<% 
								} 
							%>
						</ul>
						<%
							} else {
						%>
	               			<h1>Ooops, en este momento no hay Usuarios en el sistema</h1>
						<%
							}
						%>
                	</div>
	        	</div>
        	</div>
    	</div>
    	<jsp:include page="../template/footer.jsp" />
		<script src="media/javaScripts/listadosBusquedas.js"></script>
	</body>
</html>