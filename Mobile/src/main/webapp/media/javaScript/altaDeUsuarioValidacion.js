
const formulario = document.getElementById("formulario");
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const descripcion = document.querySelector('#formulario textarea');
const fechaNac = document.getElementById('fechaNacimiento');
const nacionalidad = document.getElementById('nacionalidad');
const email = document.getElementById('correo');
const imagen = document.getElementById('imagen');
const link = document.getElementById('link');

formulario.addEventListener("submit", function(event) {
	event.preventDefault(); 
	
	//Variables para validacion de datos		
	const nicknameValido = validarCampo(inputs[0].value, 'nickname');
	const emailValido = validarCampo(email.value, 'correo');
	const nombreValido = validarCampo(inputs[2].value, 'nombre');
	const apellidoValido = validarCampo(inputs[3].value, 'apellido');
	const contraseniaValido = validarCampo(inputs[4].value, 'contrasenia');
	const contraseniaConfValido = validarCampo(inputs[5].value, 'confirmarContrasenia');
	const tipoUsuarioValido = validarCampo(selects[0].value, 'tipoUsuario');
	const imagenValido = validarCampo(imagen.value, 'imagen');
	const linkValido = validarCampo(link.value, 'link');
	const contrasDistintasValido = validarContras();
	
	//Validacion si es una empresa
	if(tipoUsuarioValido && selects[0].value === 'empresa') {
		const descripcionValido = validarCampo(descripcion.value, 'descripcion');
		if (nicknameValido && emailValido && nombreValido && apellidoValido && contraseniaValido
			&& contraseniaConfValido && tipoUsuarioValido && descripcionValido && imagenValido
			&& linkValido && contrasDistintasValido) {
			mandarForm();
		}
		
	//Validacion si es un postulante
	} else if(tipoUsuarioValido && selects[0].value === 'postulante') {
		const nacionalidadValido = validarCampo(nacionalidad.value, 'nacionalidad');
		const fechaNacimientoValido = validarCampo(fechaNac.value, 'fechaNacimiento');
		if (nicknameValido && emailValido && nombreValido && apellidoValido && contraseniaValido
			&& contraseniaConfValido && tipoUsuarioValido && nacionalidadValido && fechaNacimientoValido
			&& imagenValido && contrasDistintasValido) {
			mandarForm();
		} 
	}
})

//Funcion para mandar formulario
function mandarForm() {
	formulario.submit();
}


const validarCampo = (valor, campo) => {
	
	const grupo = document.getElementById(`grupo__${campo}`);
	const icono = document.querySelector(`#grupo__${campo} i`);
	const mensajeError1 = document.querySelector(`#grupo__${campo} .formulario__input-error`);
	const mensajeError2 = document.querySelector(`#grupo__${campo} .formulario__input-error2`);
	
	//Si el valor del campo es vacio entro
	if(valor !== "") {
		
		//Si el correo no es valido o la url no es valida o la fecha no lo es, muestro erroes
		if((campo === "correo" && !esCorreoValido(valor)) || campo === "link" && !esURL(valor) || 
			(campo === "fechaNacimiento" && new Date() < new Date(valor))) {
			grupo.classList.add('formulario__grupo-incorrecto');
			grupo.classList.remove('formulario__grupo-correcto');
			icono.classList.add('fa-circle-xmark');
			icono.classList.remove('fa-circle-check');
			if(campo === "correo" || campo === "fechaNacimiento") {
				mensajeError1.classList.remove('formulario__input-error-activo');
				mensajeError2.classList.add('formulario__input-error-activo');
			} else if (campo === "link") {
				mensajeError1.classList.add('formulario__input-error-activo');
			}
			return false;
			
		//Si el correo es valido o el link es valido o la fecha es valido lo muestro bien
		} else if ((campo === "correo" && esCorreoValido(valor)) || campo === "link" && esURL(valor) ||
					campo === "fechaNacimiento") {
			grupo.classList.remove('formulario__grupo-incorrecto');
			grupo.classList.add('formulario__grupo-correcto');
			icono.classList.remove('fa-circle-xmark');
			icono.classList.add('fa-circle-check');
			mensajeError1.classList.remove('formulario__input-error-activo');
			if(campo === "correo" || campo === "fechaNacimiento") {
				mensajeError2.classList.remove('formulario__input-error-activo');				
			}
			return true;
		}else{ 
		
			//Si el valor del campo no es ninguno de los anteriores lo muestro bien
			grupo.classList.remove('formulario__grupo-incorrecto');
			grupo.classList.add('formulario__grupo-correcto');
			icono.classList.remove('fa-circle-xmark');
			icono.classList.add('fa-circle-check');
			if(campo !== 'imagen' && campo !== 'link')
				mensajeError1.classList.remove('formulario__input-error-activo');
			return true;
		}
	} else if (campo !== 'imagen' && campo !== 'link'){
		grupo.classList.add('formulario__grupo-incorrecto');
		grupo.classList.remove('formulario__grupo-correcto');
		icono.classList.add('fa-circle-xmark');
		icono.classList.remove('fa-circle-check');
		mensajeError1.classList.add('formulario__input-error-activo');
		return false;
	//Si el link es vacio saco el tic de correcto
	} else if(campo === 'link') {
		grupo.classList.remove('formulario__grupo-incorrecto');
		grupo.classList.add('formulario__grupo-correcto');
		icono.classList.remove('fa-circle-xmark');
		mensajeError1.classList.remove('formulario__input-error-activo');
		return true;
	} else if(campo === 'imagen' || campo === 'link') {
		return true;
	}
}

//Funcion para validar contraseñas
const validarContras = () => {
	const contra1 = document.getElementById('contrasenia');
	const contra2 = document.getElementById('confirmarContrasenia')
	const grupoC1 = document.getElementById(`grupo__contrasenia`);
	const iconoC1 = document.querySelector(`#grupo__contrasenia i`);
	const mensajeError1C1 = document.querySelector(`#grupo__contrasenia .formulario__input-error`);
	const grupoCr = document.getElementById(`grupo__confirmarContrasenia`);
	const iconoCr = document.querySelector(`#grupo__confirmarContrasenia i`);
	const mensajeError1Cr = document.querySelector(`#grupo__confirmarContrasenia .formulario__input-error`);
	
	
	if(contra1.value !== contra2.value) {
		grupoCr.classList.add('formulario__grupo-incorrecto');
		grupoCr.classList.remove('formulario__grupo-correcto');
		iconoCr.classList.add('fa-circle-xmark');
		iconoCr.classList.remove('fa-circle-check');
		mensajeError1Cr.classList.add('formulario__input-error-activo');
		return false;
	} else if (contra1.value === contra2.value && contra1.value !== "") {
		grupoC1.classList.remove('formulario__grupo-incorrecto');
		grupoC1.classList.add('formulario__grupo-correcto');
		iconoC1.classList.remove('fa-circle-xmark');
		iconoC1.classList.add('fa-circle-check');
		mensajeError1C1.classList.remove('formulario__input-error-activo');
		grupoCr.classList.remove('formulario__grupo-incorrecto');
		grupoCr.classList.add('formulario__grupo-correcto');
		iconoCr.classList.remove('fa-circle-xmark');
		iconoCr.classList.add('fa-circle-check');
		mensajeError1Cr.classList.remove('formulario__input-error-activo');
		return true;
	}
}

//Evento para mostrar inputs de la empresa o el postulante, dependiendo lo que se eliga 
document.getElementById("tipoUsuario").addEventListener("change", function() {
	var tipoUsuario = this.value;
	
	var empresaSelect = document.getElementById("grupo__desc_link");
	var empresaEstado = document.querySelector('#grupo__desc_link .desc_link-estado');
	
	var postulanteSelect = document.getElementById("grupo__nacio_fech");
	var postulanteEstado = document.querySelector('#grupo__nacio_fech .nacio_fech-estado');
	
	//Muestro emrpesa
	if(tipoUsuario === "empresa") {
		empresaSelect.disabled = false;
		empresaEstado.classList.add('desc_link-estado-activo');
		postulanteSelect.disabled = true;
		postulanteEstado.classList.remove('nacio_fech-estado-activo');
		
	//Muestro posntulante
	} else if (tipoUsuario === "postulante") {
		empresaSelect.disabled = true;
		empresaEstado.classList.remove('desc_link-estado-activo');
		postulanteSelect.disabled = false;
		postulanteEstado.classList.add('nacio_fech-estado-activo');
	
	//Si no se elige ninguno no muestro nada
	} else {
		empresaSelect.disabled = true;
		empresaEstado.classList.remove('desc_link-estado-activo');
		postulanteSelect.disabled = true;
		postulanteEstado.classList.remove('nacio_fech-estado-activo');
	}
});

function esURL(url) {
  // Expresión regular para validar una URL
  var regex = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
  return regex.test(url);
}

function esCorreoValido(correo) {
  // Expresión regular para validar una dirección de correo electrónico
  var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  return regex.test(correo);
}