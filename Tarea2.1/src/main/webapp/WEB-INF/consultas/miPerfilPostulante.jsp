<%@page import="logica.DTUsuario"%>
<%@page import="logica.DTPostulante"%>
<%@page import="logica.DTPostulacion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
	                    	<div class="col-md-3" id="postulantePerfilInfo">
                        		<img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
							</div>
							<%
	                    		DTUsuario userInfo = (DTUsuario) request.getAttribute("userData");
								DTPostulante pInfo = null;
	                    		pInfo = (DTPostulante) userInfo;
	                    		List<DTPostulacion> posts = pInfo.getPostulaciones();
							%>
							<div class="col">
                                <h2><%=pInfo.getNickname() %>></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=pInfo.getNombre() %></p>
                                <p><i class=""></i> <b>Apellido:</b><%=pInfo.getApellido() %></p>
                                <p><i class=""></i> <b>Correo: </b><%=pInfo.getCorreo() %></p>
                                <p><i class=""></i> <b>Fecha de Nacimiento: </b><%=pInfo.getFechaDeNacimiento() %></p>
                                <p><i class=""></i> <b>Nacionalidad: </b><%=pInfo.getNacionalidad() %></p>

	                               
							</div>
	                    </div>
	                    <div class ="container-fluid" style="margin-top: 1%;">
							<div class = "col-md-6 col-md-12 text-center">
		                        <h3>Postulaciones</h3>
		                        <div class="table-container">
			                        <table class="table text-center" id="tabla-postulaciones">
									  	<thead>
									    	<tr>
									      		<th >Nombre de Oferta Laboral</th>
		  							      		<th>Fecha de Postulacion</th>
									    	</tr>
									  	</thead>
									<tbody>
										<%
											for(DTPostulacion p : posts){
										%>
									    <tr>
									      	<td><a href="ConsultaOfertaLaboral?oferta_consultada=<%=p.getOferta()%>"><%=p.getOferta() %></a></td>
									      	<td><%=p.getFecha() %></td>
									    </tr>
										<%
										  	}  
										%>
									</tbody>
									</table>
		                        </div>
							</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
	</body>
</html>