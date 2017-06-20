/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (finalizarEmergencia.js)
*/


    function finalizarEmergencia(element) {

    		var table = document.getElementById('enCaminoTable');
     	  	var button = document.getElementById(element.id);
     	  	var rowvalue = button.parentNode.parentNode.rowIndex;
     	  	var cell = table.rows[rowvalue].cells[0];
     	  	var idEmergencia = cell.firstChild.data;



        	$('ul.tabs').tabs('select_tab', 'test4');

            //Seteamos el estado de la emergencia seleccionada como Finalizada.
        	var dbRefEmergencia = firebase.database().ref('emergencias/' + idEmergencia + '/estado');
        	dbRefEmergencia.transaction(function(status) {
        		status = "Finalizada";
   				return status;
			});


            dbRefEmergencia = firebase.database().ref('emergencias/' + idEmergencia + '/ambulancia');
            //Seteamos a las ambulancias asignadas a la emergencia seleccionada como Disponibles.
            dbRefEmergencia.on("child_added", function(data) {

               var dbRefAmbulancia = firebase.database().ref('ambulancias/' + data.key + '/estado' );
               dbRefAmbulancia.transaction(function(status) {
                    status = "Disponible";
                    return status;
                });

               dbRefAmbulancia = firebase.database().ref('ambulancias/' + data.key + '/emergenciaActual' );
               dbRefAmbulancia.transaction(function(actual) {
                    actual = null;  
                    return actual; 
                });
             });
            

         
   }