
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const descripcion = document.querySelector('#formulario textarea')

formulario.addEventListener('submit', function(e) {
	e.preventDefault();
	
	//Variables para validacion de datos
	const nombreValidado = validarCampo(inputs[0].value, 'nombre');
	const ciudadValidado = validarCampo(inputs[1].value, 'ciudad');
	const departamentoValidado = validarCampo(inputs[2].value, 'departamento');
	const horarioValidado = validarCampo(inputs[3].value, 'horario'); 
	const remuneracionValidada = validarCampo(inputs[4].value, inputs[4].name);
	const tipoValidado = validarCampo(selects[0].value, 'tipo');
	const keywordsValidado = validarCampo(selects[1].value, 'keywords');
	//const tipoCompraValidado = validarCampo(selects[2].value, 'tipoCompra');
	const descripcionValidado = validarCampo(descripcion.value, 'descripcion');
	
/*	//Validacion si paga con paquete
	if(tipoCompraValidado && selects[2].value === 'paquete') {
		const paqutesValidado = validarCampo(selects[3].value, 'paquetes');
		if (nombreValidado && horarioValidado && remuneracionValidada && tipoValidado
  	 		&& keywordsValidado && descripcionValidado && paqutesValidado &&tipoCompraValidado) {
			mandarForm();
		}
	
	//Validacion si paga en general
	} else*/ if (nombreValidado && horarioValidado && remuneracionValidada && tipoValidado
  	 			&& keywordsValidado && descripcionValidado /*&& tipoCompraValidado*/ && ciudadValidado && departamentoValidado) {
		mandarForm();
	}
});

//Funcion para madar el formulario
function mandarForm() {
		formulario.submit();
	/*
	
	formulario.reset();
			
	document.getElementById('formulario__mensaje-exitoso').classList.add('formulario__mensaje-exitoso-activo');
	setTimeout(() => {
		document.getElementById('formulario__mensaje-exitoso').classList.remove('formulario__mensaje-exitoso-activo');
	}, 5000);
	
	document.querySelectorAll('.formulario__grupo-correcto').forEach((icono) => {
		icono.classList.remove('formulario__grupo-correcto');
	});
	
	paquetesSelect.disabled = true;
	paquetesEstado.classList.remove('paquetes-estado-activo'); */
}

//Funcion para validar los campos
const validarCampo = (valor, campo) => {
	
	const grupo = document.getElementById(`grupo__${campo}`);
	const icono = document.querySelector(`#grupo__${campo} i`);
	const mensajeError1 = document.querySelector(`#grupo__${campo} .formulario__input-error`);
	const mensajeError2 = document.querySelector(`#grupo__${campo} .formulario__input-error2`);
	
	//Si el valor del campo es vacio entro
	if(valor !== "") {
		
		//Si la remunarcion no es valida o el horario no es valido muestro errores
		if((campo === 'remuneracion' && valor <= 0) || (campo === 'horario' && !esHorario(valor))) {
			grupo.classList.add('formulario__grupo-incorrecto');
			grupo.classList.remove('formulario__grupo-correcto');
			icono.classList.add('fa-circle-xmark');
			icono.classList.remove('fa-circle-check');
			mensajeError1.classList.remove('formulario__input-error-activo');
			mensajeError2.classList.add('formulario__input-error2-activo');
			return false;
		} 
		
		//Si esta todo bien muestro que esta correcto
		grupo.classList.remove('formulario__grupo-incorrecto');
		grupo.classList.add('formulario__grupo-correcto');
		icono.classList.remove('fa-circle-xmark');
		icono.classList.add('fa-circle-check');
		mensajeError1.classList.remove('formulario__input-error-activo');
		if((campo === 'remuneracion' && valor > 0) || (campo === 'horario' && esHorario(valor))) {
			mensajeError2.classList.remove('formulario__input-error2-activo');
		}
		return true;
	
	//Si es vacio muestro errores
	} else {
		grupo.classList.add('formulario__grupo-incorrecto');
		grupo.classList.remove('formulario__grupo-correcto');
		icono.classList.add('fa-circle-xmark');
		icono.classList.remove('fa-circle-check');
		mensajeError1.classList.add('formulario__input-error-activo');
		return false;
	}
}
/*
//Evento para mostrar el input si paga con paquete
document.getElementById("tipoCompra").addEventListener("change", function() {
	var tipoCompra = this.value;
	var paquetesSelect = document.getElementById("paquetes");
	var paquetesEstado = document.querySelector('#grupo__paquetes .paquetes-estado');
	
	if(tipoCompra == "paquete") {
		paquetesSelect.disabled = false;
		paquetesEstado.classList.add('paquetes-estado-activo');
	} else {
		paquetesSelect.disabled = true;
		paquetesEstado.classList.remove('paquetes-estado-activo');
	}
}); */

function esHorario(horario) {
	var formatoRegExp = /^([01]\d|2[0-3]):([0-5]\d) - ([01]\d|2[0-3]):([0-5]\d)$/;
	return formatoRegExp.test(horario);
}