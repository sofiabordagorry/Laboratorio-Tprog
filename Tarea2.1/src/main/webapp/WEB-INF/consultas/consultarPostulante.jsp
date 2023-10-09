<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.DTUsuario"%>
<%@page import="logica.DTPostulante"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/stylesConsultas.css" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
    	<jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	            <jsp:include page="../template/sidebar.jsp" />
				<div class="col col-md-9 col-sm-12" id="main-content">
	                <!--ACÁ VA TODO EL CONTENIDO ESPECÍFICO DE LA PÁGINA 
	                EJEMPLO:-->
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
	                		<%
		                		DTUsuario userInfo = (DTUsuario) request.getAttribute("userData");
	                			DTPostulante pInfo = null;
		                		pInfo = (DTPostulante) userInfo;
	                		%>
                    		<div class="col-md-3" id="postulanteConsultaInfo">
                        		<img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
                            </div>
                            <div class="col">
                                <h2><%= pInfo.getNickname()%></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=pInfo.getNombre() %></p>
                                <p><i class=""></i> <b>Apellido:</b><%=pInfo.getApellido() %></p>
                                <p><i class=""></i> <b>Correo: </b><%=pInfo.getCorreo() %></p>
                                <p><i class=""></i> <b>Fecha de Nacimiento: </b><%=pInfo.getFechaDeNacimiento() %></p>
                                <p><i class=""></i> <b>Nacionalidad: </b><%=pInfo.getNacionalidad() %></p>
                            </div>
	                    </div>
	                </div>
	        	</div>
	    	</div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
	</body>
</html>