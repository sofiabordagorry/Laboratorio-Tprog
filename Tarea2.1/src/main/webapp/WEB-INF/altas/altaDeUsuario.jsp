<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../template/head.jsp" />
		<link rel="stylesheet" href="media/styles/altaDeUsuario.css">
		<title>Trabajo.uy</title>
	</head>
	<body>
		<jsp:include page="../template/navbar.jsp" />
		<div class="container-fluid" id="web-content">
			<div class="row justify-content-md-center">
				<div class="col-xs-12 col-md-10">
					<div id="registrarContainer" class="d-flex flex-column min-vh-100 justify-content-center">
						
						<!-- Formulario -->
						<form action="AltaDeUsuario" method="POST" class="formulario" id="formulario">
							<h3>Registro de Usuario</h3>
							<br>
							
							<!-- Nickname y Email -->
							<div class="form-group">
								<div class="row">
									
									<!-- Nickname -->
									<div class="col-md-6 formulario__grupo" id="grupo__nickname">
										<label for="nickname">Nickname</label>
										<div class="input-group">
											<input type="text" class="form-control" name="nickname" id="nickname" placeholder="Ingresar nickname" value="jokin">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar un nickname.</p>
									</div>
									
									<!-- Email -->
									<div class="col-md-6 formulario__grupo" id="grupo__correo">
										<label for="correo">Email</label>
										<div class="input-group">
											<input type="text" class="form-control" name="correo" id="correo" placeholder="correo@correo.com" value="Jokin@gmail.com">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar un email.</p>
										<p class="formulario__input-error2">Debe ingresar un mail válido.</p>
									</div>
								</div>
							</div>
							
							<!-- Nombre y Apellido -->
							<div class="form-group">
								<div class="row">
									
									<!-- Nombre -->
									<div class="col-md-6 formulario__grupo" id="grupo__nombre">
										<label for="nombre">Nombre</label>
										<div class="input-group">
											<input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="Joaquin">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar un nombre.</p>
									</div>
									
									<!-- -->
									<div class="col-md-6 formulario__grupo" id="grupo__apellido">
										<label for="apellido">Apellido</label>
										<div class="input-group">
											<input type="text" class="form-control" name="apellido" id="apellido" placeholder="Apellido" value="Corbo">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>											
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar un apellido.</p>
									</div>
								</div>
							</div>
							
							<!-- Contraseña y confirmacion de contraseña -->
							<div class="form-group">
								<div class="row">
									
									<!-- Contraseña -->
									<div class="col-md-6 formulario__grupo" id="grupo__contrasenia">
										<label for="contrasenia">Contraseña</label>
										<div class="input-group">
											<input type="password" class="form-control" name="contrasenia" id="contrasenia" placeholder="Contraseña" value="123">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar una contraseña.</p>
									</div>
									
									<!-- Confirmacion de contraseña -->
									<div class="col-md-6 formulario__grupo" id="grupo__confirmarContrasenia">
										<label for="confirmarContrasenia">Confirmar contraseña</label>
										<div class="input-group">
											<input type="password" class="form-control" name="contrasenia" id="confirmarContrasenia" placeholder="Confirmar contraseña" value="123">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
											</div>
										</div>
										<p class="formulario__input-error">Las contraseñas no coinciden.</p>
									</div>
								</div>
							</div>
							
							<!-- Imagen -->
							<div class="form-group formulario__grupo" id="grupo__imagen">
								<label for="imagen" class="form-label">Imagen (opcional)</label>
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" name="imagen" id="imagen" title="Buscar" accept=".jpg, .jpeg, .png">
										<label class="custom-file-label" for="imagen">Seleccionar archivo</label>
										<div class="input-group-append">
											<i class="formulario__validacion-estado formulario__validacion-estado-file fa-solid fa-circle-xmark"></i>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Tipo de Usuario -->
							<div class="form-group formulario__grupo" id="grupo__tipoUsuario">
								<label for="tipoUsuario" class="form-label">Seleccionar tipo de Usuario</label>
								<div class="input-group">
									<select class="form-select form-select-lg form-control custom-select" name="tipoUsuario" id="tipoUsuario">
										<option selected disabled value="">Elegir...</option>
										<option value="empresa">Empresa</option>
										<option value="postulante">Postulante</option>
									</select>
									<div class="input-group-append">
										<i class="formulario__validacion-estado formulario__validacion-estado-select fa-solid fa-circle-xmark"></i>
									</div>
								</div>
								<p class="formulario__input-error">Debe elegir un tipo de Usuario.</p>
							</div>
							
							<!-- Informacion adicional si elige Empresa -->
							<div class="formulario__grupo" id="grupo__desc_link">
								<div class="form-group desc_link-estado">
									<div class="row">
										
										<!-- Descripcion -->
										<div class="col-md-7" id="grupo__descripcion">
											<label for="descripcion" class="form-label">Descripcion de la Empresa</label>
											<div class="input-group">
												<textarea class="form-control" name="descripcion" id="descripcion">Pro player del CS2 y el VALORANT</textarea>
												<div class="input-group-append">
													<i class="formulario__validacion-estado formulario__validacion-estado-caja fa-solid fa-circle-xmark"></i>
												</div>
											</div>
											<p class="formulario__input-error">Ingrese una descripcion.</p>
										</div>
										
										<!-- Link -->
										<div class="col-md-5" id="grupo__link">
											<label for="link" class="form-label">Link (opcional)</label>
											<div class="input-group">
												<input type="text" class="form-control" name="link" id="link">
												<div class="input-group-append">
													<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
												</div>
											</div>
											<p class="formulario__input-error">Debe ingresar un link válido.</p>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Informacion adicional si elige Posutlante -->
							<div class="formulario__grupo" id="grupo__nacio_fech">
								<div class="form-group nacio_fech-estado">
									<div class="row">
										
										<!-- Nacionalidad -->
										<div class="col-md-6" id="grupo__nacionalidad">
											<label for="nacionalidad" class="form-label">Nacionalidad</label>
											<div class="input-group">
												<input type="text" class="form-control" name="nacionalidad" id="nacionalidad">
												<div class="input-group-append">
													<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
												</div>
											</div>
											<p class="formulario__input-error">Ingrese una nacionalidad.</p>
										</div>
										
										<!-- Fecha de nacimiento -->
										<div class="col-md-6" id="grupo__fechaNacimiento">
											<label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
											<div class="input-group">
												<input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento">
												<div class="input-group-append">
													<i class="formulario__validacion-estado formulario__validacion-estado-fecha fa-solid fa-circle-xmark"></i>
												</div>
											</div>
											<p class="formulario__input-error">Ingrese una fecha de nacimiento.</p>
											<p class="formulario__input-error2">La fecha es mayor a la actual.</p>
										</div>
									</div>
								</div>
							</div>
							<div class="formulario__grupo formulario__grupo-btn-registrarse">
								<button type="submit" class="btn">Registrarse</button>
								<p class="formulario__mensaje-exitoso" id="formulario__mensaje-exitoso">
									Usuario registrado con exito.
								</p>
							</div>
						</form>
		           	</div>				
				</div>
			</div>
		</div>
		
		<jsp:include page="../template/footer.jsp" /> 
		<script src="media/javaScript/altaDeUsuarioValidacion.js"></script>
	</body>
</html>