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
var table = document.getElementById('enCaminoTableBody');

          
    var cont = 0;
    //Cargamos los datos de la DB de Firebase.
    var dbRefObject = firebase.database().ref('emergencias/');
    dbRefObject.orderByChild("estado").equalTo("En Camino").on("child_added", function(data) {

        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0); 
        cell1.innerHTML = data.key;
        var cell2 = row.insertCell(1);
        cell2.innerHTML = data.val().numAmbulancia;
        var cell2 = row.insertCell(2);
        cell2.innerHTML = data.val().lugarAccidente;
        var cell3 = row.insertCell(3);
        cell3.innerHTML = data.val().fechaRegistro;
        var cell4 = row.insertCell(4);
        cell4.innerHTML = data.val().suceso;
        var cel5 = row.insertCell(5);
        cel5.innerHTML = '<a class="waves-effect waves-light btn" id="finalizarbtn' + cont + '" onclick="finalizarEmergencia(this); return false;">Finalizar</a>';
        cont += 1;
});


//Obtenemos las emergencias finalizadas.

    $(document).ready(function(){
          var dbRefObjectFinalizada = firebase.database().ref('emergencias/');
          var table = $('#finalizadaTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
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
          var dbRefObjectFinalizada = firebase.database().ref('pacientes/');
          var tablePacientes = $('#pacientesTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();

          dbRefObjectFinalizada.on("child_added", function(data) {
          var dataSet = [data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo];
          tablePacientes.rows.add([dataSet]).draw();  
          
        });

          dbRefObjectFinalizada.on("child_changed", function(data) {
          var dataSet = [data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo];
          tablePacientes.rows.add([dataSet]).draw();  
          
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              tablePacientes.search( $(this).val() ).draw();
           })       


      });//Fin JQuery