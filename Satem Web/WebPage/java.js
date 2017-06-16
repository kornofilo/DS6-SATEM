window.onload =inicializar;
var formambulancias;
var refambulancias;
var tbodytablaambulancias;
function inicializar() {
 	formambulancias = document.getElementById("form-ambulancias");
 	formambulancias.addEventListener("submit",enviarConvalidacionAFirebase, false);
     


    tbodytablaambulancias=document.getElementById("tabla-ambulancias"); 

    refambulancias=firebase.database().ref().child("ambulancias");

    mostrarambulancias();
 } 
  function mostrarambulancias() {
  	refambulancias.on("value",function(snap){
    var datos=snap.val();
    var filasamostrar="";
    for (var key in datos) {
    	filasamostrar+="<tr>"+
	    	               "<td>"+ datos[key].cantidadEmergencias  +"</td>"+
	    	               "<td>"+ datos[key].emergenciaActual  +"</td>"+
	    	               "<td>"+ datos[key].estado  +"</td>"+
                       "</tr>";
    }
    tbodytablaambulancias.innerHTML= filasamostrar;
  	});
  }
 function enviarConvalidacionAFirebase(event) {
 	event.preventDefault();
 	refambulancias.push({
 		cantidadEmergencias: event.target.canidaddeemergencias.value,
 		emergenciaActual: event.target.Emergenciaactual.value, 
 		estado: event.target.estado.value
 	})
 	formambulancias.reset();
 	// body...
 }