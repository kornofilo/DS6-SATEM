/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos las emergencias Registradas.
$(document).ready(function() {

  var $selectDropdown = 
      $("#selectEmergencia");


    //Cargamos los datos de la DB de Firebase.
    var dbRefObjectERegistradas = firebase.database().ref('emergencias/');
    dbRefObjectERegistradas.orderByChild("estado").equalTo("Registrada").on("child_added", function(data) {
     $selectDropdown.append(
      $("<option></option>")
        .attr("value",data.key)
        .text(data.val().suceso + " @ " + data.val().lugarAccidente)
     );
    });



    $('select').material_select();

});

//Obtenemos las ambulancias disponibles.

$(document).ready(function() {

  var $selectDropdown = 
      $("#selectAmbulancias");


    //Cargamos los datos de la DB de Firebase.
    var dbRefObjectERegistradas = firebase.database().ref('ambulancias/');
    //Child_added: Agrega una ambulancia al select cada vez que una ambulancia pasa al estado "Disponible."
    dbRefObjectERegistradas.orderByChild("estado").equalTo("Disponible").on("child_added", function(data) {
     $selectDropdown.append(
      $("<option></option>")
        .attr("value",data.key)
        .text(data.key)
      );

    });
    //Child_removed: Elimina del select a aquellas ambulancias que han pasado al estado de "Ocupadas".
    dbRefObjectERegistradas.orderByChild("estado").equalTo("Disponible").on("child_removed", function(data) {
      $("#selectAmbulancias option[value='" + data.key +"']").remove();
    });

   


    $('select').material_select();

});
    



//Obtenemos las emergencias en Camino.
     $(document).ready(function(){
          var cont = 0;
          var dbRefObjectEnCamino = firebase.database().ref('emergencias/');
          var table = $('#enCaminoTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
           "language": {
                 "emptyTable": "No se encuentran emergencias en camino."
              }
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();

          dbRefObjectEnCamino.orderByChild("estado").equalTo("En Camino").on("child_added", function(data) {
          var dataSet = [data.key,data.val().numAmbulancia,data.val().lugarAccidente,data.val().fechaRegistro, data.val().suceso,'<a class="waves-effect waves-light btn orange" id="finalizarbtn' + cont + '" onclick="finalizarEmergencia(this); return false;">Finalizar</a>'];
          table.rows.add([dataSet]).draw(); 
                  cont += 1; 
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              table.search( $(this).val() ).draw();
           })       


      });//Fin JQuery


//Obtenemos las emergencias finalizadas.

    $(document).ready(function(){
          var dbRefObjectFinalizada = firebase.database().ref('emergencias/');
          var table = $('#finalizadaTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
          "language": {
                 "emptyTable": "No se encuentran emergencias finalizadas."
              }
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();

          dbRefObjectFinalizada.orderByChild("estado").equalTo("Finalizada").on("child_added", function(data) {
          var dataSet = [data.key,data.val().numAmbulancia,data.val().lugarAccidente,data.val().fechaRegistro, data.val().suceso];
          table.rows.add([dataSet]).draw();  
          
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              table.search( $(this).val() ).draw();
           })       


      });//Fin JQuery

//Obtenemos a los pacientes registrados.

$(document).ready(function(){
          var dbRefObjectPacientes = firebase.database().ref('pacientes/');
          var tablePacientes = $('#pacientesTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
           "language": {
                 "emptyTable": "No se encuentran pacientes."
              }
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();


          dbRefObjectPacientes.on('child_added', function(data) {    
           var rowIndex = $('#pacientesTable').dataTable().fnAddData([data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo]);
           var row = $('#pacientesTable').dataTable().fnGetNodes(rowIndex);
           $(row).attr('id', data.key);
        });


       dbRefObjectPacientes.on("child_changed", function(data) {
           $('#pacientesTable').dataTable().fnUpdate( [data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo], document.getElementById(data.key), undefined, false, false);
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              tablePacientes.search( $(this).val() ).draw();
           })       


      });//Fin JQuery