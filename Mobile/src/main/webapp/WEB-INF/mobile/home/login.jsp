<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.LoginEstado" %>
<%@ page import="publicar.DtUsuario" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/inicioSesion.css" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
	    <!-- Barra de navegación -->
	    <jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	            <div class="col-xs-12 col-md-10">
					<div id="loginContainer" class="d-flex flex-column min-vh-100 justify-content-center">
	                	<%
	                		DtUsuario usrlog = (DtUsuario) session.getAttribute("usuario_logueado");
	                		if (usrlog == null) {
	                	%>
	                    <form action="Login" method="POST" id="loginForm">
	                        <h3>Ingrese a su cuenta</h3>
	                        <div class="form-group">
	                          	<label for="InputNicknameEmail">Nickname o e-mail del postulante</label>
	                          	<input type="nickname" class="form-control" name="login" id="InputNicknameEmailLogin" placeholder="Ingresar nickname o e-mail" required>
	                        </div>
	                        <div class="form-group">
	                          	<label for="InputPassword">Contraseña</label>
	                          	<input type="password" class="form-control" name="password" id="InputPasswordLogin" placeholder="Contraseña" required>
	                        </div>
							<button type="submit" class="btn">Iniciar sesión</button>
							<%
		                      	LoginEstado error = (LoginEstado) session.getAttribute("estado_sesion");
		                        if (error != null && error != LoginEstado.NO_LOGIN) {
		                    %>
		                    <div class="error-message">
		                      	<%= error %>
		                    </div>
						</form>
		                    <%
		                      	session.removeAttribute("loginError");
		                        }
		                    %>
		                    <%
		                		} else {
		                    %>
							<div>
		                      	<h1>Ocurrió un error!</h1>
		                      	<p>Ya existe una sesión iniciada</p>
							</div>
	                      	<%
	                			}
	                      	%>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
 	</body>
</html>
