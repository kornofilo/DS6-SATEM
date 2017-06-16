window.onload =inicializar;
var refambulancias;
var tbodytablaambulancias;
function inicializar() {
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
 }