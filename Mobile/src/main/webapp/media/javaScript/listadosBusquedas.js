document.addEventListener("DOMContentLoaded", function () {
    const listaElementos = document.getElementById("lista-elementos");
    const buscador = document.getElementById("buscador");

    // Maneja el evento de cambio en el campo de búsqueda (a medida que se escribe)
    buscador.addEventListener("input", function () {
        const textoBusqueda = buscador.value.toLowerCase().trim();

        // Recorre los elementos de la lista y muestra/oculta según la búsqueda
        Array.from(listaElementos.children).forEach(function (elemento) {
            const enlace = elemento.querySelector("a");
            const textoEnlace = enlace.textContent.toLowerCase();

            if (textoBusqueda === "" || textoEnlace.includes(textoBusqueda)) {
                elemento.style.display = "list-item";
            } else {
                elemento.style.display = "none";
            }
        });
    });
});