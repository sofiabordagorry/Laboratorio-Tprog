// Obtener referencias a elementos del formulario
const formulario = document.getElementById("formularioPos");
const CVReducido = document.getElementById('CVReducido');
const motivacion = document.getElementById('motivacion');
const link = document.getElementById('link');

// Define una función para mostrar u ocultar mensajes de error
const mostrarError = (grupo, mensaje, mostrar) => {
  const mensajeError = grupo.querySelector('.formulario__input-error');
  mensajeError.textContent = mensaje;
  mensajeError.style.display = mostrar ? 'block' : 'none';
};

// Define una función para validar un campo
const validarCampo = (valor, campo) => {
  const grupo = document.getElementById(`grupo__${campo}`);

  if (valor === "") {
    mostrarError(grupo, `Debe ingresar ${campo}.`, true);
    return false;
  } else {
    mostrarError(grupo, "", false);
    return true;
  }
};

const validarEnlaceYouTube = (valor) => {
  const grupo = document.getElementById("grupo__link");

  // Expresión regular para validar enlaces de YouTube
const regexEnlaceYouTube = /^(https:\/\/)?(www\.)?youtube\.com\/(watch\?v=|embed\/|v\/)?[A-Za-z0-9_-]+(&\S+)?$/;

  if (!valor) {
    // Si el campo está vacío, no mostramos un mensaje de error
    mostrarError(grupo, "", false);
    return true;
  } else if (!regexEnlaceYouTube.test(valor)) {
    mostrarError(grupo, "Debe ingresar un enlace de YouTube válido.", true);
    return false;
  } else {
    mostrarError(grupo, "", false);
    return true;
  }
};

// Agregar un evento al botón "Postularme" para la validación
const botonPostularme = document.getElementById("botonPostularme");
formulario.addEventListener("submit", function(event) {
  event.preventDefault();
  // Variables para validación de datos
  const CVReducidoValido = validarCampo(CVReducido.value, 'CVReducido');
  const motivacionValido = validarCampo(motivacion.value, 'motivacion');
  const linkValido = validarEnlaceYouTube(link.value);

  // Verificar si todos los campos son válidos antes de realizar la acción
  if (CVReducidoValido && motivacionValido && linkValido) {


    // Realiza aquí la acción que deseas (por ejemplo, enviar el formulario)
    formulario.submit();
  } 
});
