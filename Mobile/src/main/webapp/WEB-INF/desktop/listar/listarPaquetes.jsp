<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
							if ((boolean) request.getAttribute("hayPaqs")){
						%>
						
						
						
						<h1 class="text-center">Busqueda de Paquetes</h1>
						<div id="barraBusqueda" class="mx-auto">
							<div class="input-group mb-3">
	            				<input type="text" id="buscador" class="form-control" placeholder="Buscar paquete">
	       					</div>
						</div>
						<ul class="list-group" id="lista-elementos">
							<%
								String[] paqs = (String[]) request.getAttribute("paquetes");
								for (int i = 0; i < paqs.length; i++){
							%>
							<li class="list-group-item text-center"><a href="?paqueteConsultado=<%=paqs[i]%>"><%=paqs[i]%></a></li>
							<% 
								} 
							%>
						</ul>
						
						<% 
							} else {
						%>
						<h1 class="text-center">Oops, en este momento no hay ningun paquete para consultar.</h1>
						<%
							}
						%>
                	</div>
	        	</div>
        	</div>
    	</div>
    	<jsp:include page="../template/footer.jsp" />
		<script src="media/javaScript/listadosBusquedas.js"></script>
	</body>
</html>