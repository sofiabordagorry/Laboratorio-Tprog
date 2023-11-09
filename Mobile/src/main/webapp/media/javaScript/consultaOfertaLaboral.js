const formulario = document.getElementById('formulario');

formulario.addEventListener('submit', function(event) {
	event.preventDefault();
    var inputs = document.getElementsByClassName('ranking');
    var rankings = [];
    var isValid = true;
    var maxRanking = inputs.length;

    for (var i = 0; i < inputs.length; i++) {
        var ranking = inputs[i].value;

        // Comprueba si el campo está vacío
        if (!ranking) {
            alert('Por favor, rellena todos los campos.');
            isValid = false;
            break;
        }

        // Comprueba si el ranking es mayor que el máximo
        if (ranking > maxRanking) {
            alert('El ranking no puede ser mayor que ' + maxRanking);
            isValid = false;
            break;
        }

        // Comprueba si el ranking ya ha sido asignado
        if (rankings.includes(ranking)) {
            alert('El ranking ' + ranking + ' ya ha sido asignado. Por favor, asigna un ranking único a cada postulante.');
            isValid = false;
            break;
        }
	
        rankings.push(ranking);
    }
	console.log(isValid);
    // Si no es válido, evita que se envíe el formulario
    if (isValid) {
        formulario.submit();
    }
});








/*const formulario = document.getElementById('formulario');

//Verificacion para que no elija el mismo numero de ranking
var rankings = document.getElementsByClassName('ranking');
for(var i = 0; i < rankings.length; i++){
    rankings[i].addEventListener('blur', function(){
	    // Limpiar todos los mensajes de error
	    for(var j = 0; j < rankings.length; j++){
	        var errorDiv = document.getElementById('error_' + rankings[j].id.split('_')[1]);
	        errorDiv.innerText = '';
	    }
	    // Comprobar si el ranking está duplicado
	    for(var j = 0; j < rankings.length; j++){
	        if(rankings[j] != this && rankings[j].value == this.value){
	            var errorDiv = document.getElementById('error_' + this.id.split('_')[1]);
	            errorDiv.innerText = 'Numero ya seleccionado, ponga otro.';
	            break;
	        }
	    }
	});
}

    document.getElementById('formulario').addEventListener('submit', function(event){
        var rankings = document.getElementsByClassName('ranking');
        for(var i = 0; i < rankings.length; i++){
            if(rankings[i].value == ''){
                alert('Por favor, selecciona un ranking para todos los postulantes antes de enviar.');
                event.preventDefault(); // Evitar que se envíe el formulario
                return;
            }
        }
    });*/

