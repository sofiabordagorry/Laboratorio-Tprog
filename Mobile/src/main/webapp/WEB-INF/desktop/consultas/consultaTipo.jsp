<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="publicar.DtTipo"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../template/head.jsp" />
		<title>Trabajo.uy</title>
		<link rel="stylesheet" href="media/styles/stylesConsultas.css">
	</head>
	<body>
		<jsp:include page="../template/navbar.jsp" />
		<div class="container-fluid" id="web-content">
        	<div class="row justify-content-md-center">
        		<jsp:include page="../template/sidebar.jsp" />
        		<div class="col col-md-9 col-sm-12" id="main-content">
		            <div class="container-fluid" id="main-container">
		            	<div class="row">
							<div class="col">
								<%
		                        	boolean noTypes = (boolean) request.getAttribute("NoTypesInSystem_Error");
		                        	if(!noTypes){
			                        	DtTipo typeData = (DtTipo) request.getAttribute("typeData");
		                        %>
		                        <h2><%=typeData.getNombre()%></h2>
	                            <p><i class="" style="color: #000000;"></i> <b>Descripcion:</b><%=typeData.getDescripcion() %></p>
	                            <p><i class=""></i> <b>Exposición:</b><%=typeData.getExposicion() %></p>
	                            <p><i class=""></i> <b>Duración: </b><%=typeData.getDuracion() %></p>
	                            <p><i class=""></i> <b>Costo: </b><%=typeData.getCosto() %></p>
	                            <p><i class=""></i> <b>Fecha de Alta: </b><%=typeData.getFechaDeAlta() %></p>
		                        <%
		                        	} else {
		                       	%>
                        		<h1>Ooops, en este momento no hay Tipos en el sistema</h1>
                        		<button href="Home">Inicio</button>
		                        <%
		                        	}
		                        %>
							</div>
						</div>
					</div>
				</div>
        	</div>
		</div>
		<jsp:include page="../template/footer.jsp" />
	</body>
</html>