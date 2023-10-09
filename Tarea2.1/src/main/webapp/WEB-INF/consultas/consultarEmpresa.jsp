<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.DTUsuario"%>
<%@page import="logica.DTEmpresa"%>
<%@page import="logica.DTOfertaLaboral"%>
<%@page import="logica.EstadoOL"%>
<%@page import="java.util.Map"%>
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
                			<div class="col-md-3" id="empresaConsultaInfo">
                    			<img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
                        	</div>
	                        <%
	                        	DTUsuario userInfo = (DTUsuario) request.getAttribute("userData");
	                        	DTEmpresa eInfo = null;
	                        	eInfo = (DTEmpresa) userInfo;
	                        	Map<String, DTOfertaLaboral> offers = eInfo.getDTOfertasLaborales();
	                        %>
	                        <div class="col">
	                            <h2><%= eInfo.getNickname()%></h2>
	                            <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=eInfo.getNombre() %></p>
	                            <p><i class=""></i> <b>Apellido:</b><%=eInfo.getApellido() %></p>
	                            <p><i class=""></i> <b>Correo: </b><%=eInfo.getCorreo() %></p>
	                            <p><i class=""></i> <b>Descripcion: </b><%=eInfo.getDescripcion() %></p>
	                            <p><i class=""></i> <b>Link: </b> <a href="https://www.ecotechinnovations.com/"><%=eInfo.getLink() %></a></p>
	
	                        </div>
                    	</div>
                    	<div class ="row" style="margin-top: 1%;">
							<div class = "col-md-6 col-md-12 text-center">
                        		<h3>Ofertas Laborales</h3>
                        		<div class="table-container">
	                        		<table class="table" id="ofertas-laborales-consulta-empresa">
										<thead>
											<th>Nombre</th>
											<th>Departamento</th>
										</thead>
										<tbody>
											<%	
											
												for(Map.Entry<String, DTOfertaLaboral> entry : offers.entrySet()){
													if (entry.getValue().getEstado() == EstadoOL.Confirmada) {
											%>
											<tr>
												<td><a href="ConsultaOfertaLaboral?oferta_consultada=<%=entry.getValue().getNombre() %>"><%=entry.getValue().getNombre() %></a></td>
												<td><%=entry.getValue().getDepartamento() %></td>
											</tr>
											<%
													}
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