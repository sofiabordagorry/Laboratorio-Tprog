    function redirigirAInicio() {
        window.location.href = "Home"; // Reemplaza "/ruta-de-tu-pagina-de-inicio" con la URL de tu página de inicio
    }

    // Verificar si hay un mensaje de postulación en la página
    var mensajePostulacion = document.getElementById("formulario__mensaje-exitoso");
    
    if (mensajePostulacion) {
        // Si hay un mensaje, programar la redirección después de 3 segundos (3000 milisegundos)
        setTimeout(redirigirAInicio, 5000); 
    }