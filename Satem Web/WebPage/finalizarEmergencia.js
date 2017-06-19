/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (finalizarEmergencia.js)
*/


    function finalizarEmergencia(element) {

    		var table = document.getElementById('enCaminoTable');
     	  	console.log(element);
     	  	var button = document.getElementById(element.id);
     	  	var rowvalue = button.parentNode.parentNode.rowIndex;
     	  	var cell = table.rows[rowvalue].cells[0];
     	  	var idEmergencia = cell.firstChild.data;
        	console.log(idEmergencia);



        	$('ul.tabs').tabs('select_tab', 'test4');

        	var dbRefEmergencia = firebase.database().ref('emergencias/' + idEmergencia + '/estado');
        	console.log(dbRefEmergencia);
        	dbRefEmergencia.transaction(function(status) {
        		status = "Finalizada";
   				return status;
			});

         
   }