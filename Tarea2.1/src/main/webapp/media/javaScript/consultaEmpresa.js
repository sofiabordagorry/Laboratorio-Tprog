$(document).ready(function () {
    $("#tablaSecundaria1, #tablaSecundaria2").hide();

    $("#tablaPrincipal tbody tr").click(function () {
        $("#tablaSecundaria1, #tablaSecundaria2").hide();
        var indiceFila = $(this).index();
        if (indiceFila === 0) {
            $("#tablaSecundaria1").show();
        } else if (indiceFila === 1) {
            $("#tablaSecundaria2").show();
        }
    });
});