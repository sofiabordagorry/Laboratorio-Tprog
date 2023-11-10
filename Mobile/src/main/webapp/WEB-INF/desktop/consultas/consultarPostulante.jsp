<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtUsuario"%>
<%@page import="publicar.DtPostulante"%>
<%@page import="java.util.Base64" %>
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
				<div class="col col-md-9 col-sm-12" id="main-content">
	                <!--ACÁ VA TODO EL CONTENIDO ESPECÍFICO DE LA PÁGINA 
	                EJEMPLO:-->
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
	                		<%
		                		DtUsuario userInfo = (DtUsuario) request.getAttribute("userData");
	                			DtPostulante pInfo = null;
		                		pInfo = (DtPostulante) userInfo;
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

                            <% if ((boolean) request.getAttribute("logueado")){
                            	Boolean siguiendo = (Boolean) request.getAttribute("siguiendo");
                            	if (siguiendo != null && siguiendo){%>
	                            		
                            		<form action="ConsultaUsuario" method="POST">
								        <input type="hidden" name="seguidor" value="<%=pInfo.getNickname() %>">
								        <button type="submit" class="btn btn-primary">Dejar de seguir</button>
								    </form>
	                            		
	                            <%} else{%>
	                            
	                            	<form action="ConsultaUsuario" method="POST">
								        <input type="hidden" name="seguidor" value="<%=pInfo.getNickname() %>">
								        <button type="submit" class="btn btn-primary">Seguir</button>
								    </form>
	                            	
	                            	
	                            <%}
	                        }%>
							</div>
                            
	                    </div>
	                </div>
	        	</div>
	    	</div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
	</body>
</html>