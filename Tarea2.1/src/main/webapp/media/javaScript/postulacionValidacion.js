// Obtener referencias a elementos del formulario
const formulario = document.getElementById("formulario");
const CVReducido = document.getElementById('CVReducido');
const motivacion = document.getElementById('motivacion');

// Define una función para mostrar u ocultar mensajes de error
const mostrarError = (grupo, mensaje, mostrar) => {
  const mensajeError = grupo.querySelector('.formulario__input-error');
  mensajeError.textContent = mensaje;
  mensajeError.style.display = mostrar ? 'block' : 'none';
};

// Define una función para validar un campo
const validarCampo = (valor, campo) => {
  const grupo = document.getElementById(`grupo__${campo}`);

  if (valor.trim() === "") {
    mostrarError(grupo, `Debe ingresar ${campo}.`, true);
    return false;
  } else {
    mostrarError(grupo, "", false);
    return true;
  }
};

// Agrega un evento al formulario para la validación
formulario.addEventListener("submit", function(event) {
  event.preventDefault();

  // Variables para validación de datos
  const CVReducidoValido = validarCampo(CVReducido.value, 'CVReducido');
  const motivacionValido = validarCampo(motivacion.value, 'motivacion');
  
  // Elemento para el mensaje de éxito
  const mensajeExito = document.getElementById("formulario__mensaje-exitoso");

  // Verificar si todos los campos son válidos antes de enviar el formulario
  if (CVReducidoValido && motivacionValido) {
    // Mostrar el mensaje de éxito
    mensajeExito.style.display = 'block';
    
    // Aquí puedes enviar el formulario si es válido
    // Ejemplo: formulario.submit();
  } else {
    // Si hay errores en algún campo, muestra un mensaje de error general o realiza alguna otra acción.
    alert("Hay errores en el formulario. Por favor, corrígelos.");
  }
});
