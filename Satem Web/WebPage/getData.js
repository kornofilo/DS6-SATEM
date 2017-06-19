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
        .text(data.val().suceso + " En " + data.val().lugarAccidente)
     );
    });

    $('select').material_select();

});

//Obtenemos las ambulancias disponibles.
var cantAmbulancias = 0;

$(document).ready(function() {

  var $selectDropdown = 
      $("#selectAmbulancias");


    //Cargamos los datos de la DB de Firebase.
    var dbRefObjectERegistradas = firebase.database().ref('ambulancias/');
    dbRefObjectERegistradas.orderByChild("estado").equalTo("Disponible").on("child_added", function(data) {
     $selectDropdown.append(
      $("<option></option>")
        .attr("value",data.key)
        .text(data.key)
      );
      cantAmbulancias += 1;
    });

    if (cantAmbulancias == 0) {
         $selectDropdown.append(
        $("<option disabled></option>")
        .text("No Hay Ambulancias Disponibles")
      );

    }



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



