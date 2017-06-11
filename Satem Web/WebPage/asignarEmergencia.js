/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (asignarEmergencia.js)
*/


    function asignarEmergencia() {
       var updates = {};
       var selectEmergency = document.getElementById("selectEmergencia");
       var values = $('#selectAmbulancias').val();
       	console.log(values.length + selectEmergency.value);  
       	//Validamos los campos del formulario.
       if(selectEmergency.value != undefined && values.length > 0){

	       //Seteamos el estado de las ambulancias seleccionadas como ocupadas.
	       for(var i=0;i < values.length; i++){
	        //Recuperamos las emergencias seleccionadas en el formulario.
	          var dbRefObject = firebase.database().ref('ambulancias/' + values[i])

	              
	           
	           dbRefObject.update({
	   				estado: "Ocupada",
	   				ultimaEmergencia: selectEmergency.value,
				});
	        }



	        //Actualizamos la emergencia asignada.
	         var dbRefEmergencia = firebase.database().ref('emergencias/' + selectEmergency.value);
	          dbRefEmergencia.on('value', function(snapshot) {
	                 console.log(snapshot.val());  
	                  var postData = {
	                  numAmbulancia: values.join(),
			          lugarAccidente: snapshot.val().lugarAccidente,
			          suceso : snapshot.val().suceso,
			          estado : "En Camino",
			          fechaRegistro: snapshot.val().fechaRegistro
			        };
	               updates['/emergencias/' + selectEmergency.value] = postData;           
				});
	         
	          console.log(updates);

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