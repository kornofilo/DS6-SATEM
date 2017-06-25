/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (asignarEmergencia.js)
*/


    function asignarEmergencia() {
       var updates = {};
       var selectEmergency = document.getElementById("selectEmergencia");
       var ambulancia = {};
       var values = $('#selectAmbulancias').val();
       	console.log(values.length + selectEmergency.value);  
       	//Validamos los campos del formulario.
       if(selectEmergency.value != undefined && values.length > 0){

	       //Seteamos el estado de las ambulancias seleccionadas como ocupadas.
	       for(var i=0;i < values.length; i++){
	       	
	       		ambulancia[values[i]] = values[i];
	       	
	        //Recuperamos las emergencias seleccionadas en el formulario.
	          var dbRefAmbulancias = firebase.database().ref('ambulancias/' + values[i]);
	          dbRefAmbulancias.once('value', function(snapshot) {
	                 console.log(snapshot.val());  
	                  var postDataAmbulancia = {
	                  estado: "Ocupada",	                 
					  emergeciaActual: selectEmergency.value,
					  cantidadEmergencias: parseInt(snapshot.val().cantidadEmergencias) + 1
			        };
	               updates['/ambulancias/' + values[i]] = postDataAmbulancia;           
				});
	        }//Fin del ciclo for.

	        //Actualizamos la emergencia asignada.
	         var dbRefEmergencia = firebase.database().ref('emergencias/' + selectEmergency.value);
	          dbRefEmergencia.on('value', function(snapshot) {
	                 console.log(snapshot.val());  
	                  var postData = {
	                  numAmbulancia: values.join(),
			          lugarAccidente: snapshot.val().lugarAccidente,
			          suceso : snapshot.val().suceso,
			          ambulancia,
			          estado : "En Camino",
			          fechaRegistro: snapshot.val().fechaRegistro
			        };
	               updates['/emergencias/' + selectEmergency.value] = postData;           
				});
	         
	          //Agregamos la nueva emergencia.
	          firebase.database().ref().update(updates,function(error) {
	                if (error) {
	                alert("Error al asignar la emergencia: " + error);
	                } else {
	                    alert("La emergencia ha sido asignada exitosamente.");
	                    console.log(firebase.database().ref().update(updates));
	                    //Nos desplazamos a la tab de emergencias en camino.
	        			 $(document).ready(function(){
	            	    	$('ul.tabs').tabs('select_tab', 'test3');
	            	   		});
	                	}
	           });

         }else {
         	alert("Por favor, rellene todos los campos.");
         }
         
     }