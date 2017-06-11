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

          

    //Cargamos los datos de la DB de Firebase.
    var dbRefObject = firebase.database().ref('emergencias/');
    var cont = 0;
    dbRefObject.orderByChild("estado").equalTo("En Camino").on("child_added", function(data) {
        cont += 1;

        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0); 
        cell1.innerHTML = cont;
        var cell2 = row.insertCell(1);
        cell2.innerHTML = data.val().nombre;
        var cell2 = row.insertCell(2);
        cell2.innerHTML = data.val().cedula;
        var cell3 = row.insertCell(3);
        cell3.innerHTML = data.val().numAmbulancia;
        var cell4 = row.insertCell(4);
        cell4.innerHTML = data.val().lugarAccidente;
        var cell5 = row.insertCell(5);
        cell5.innerHTML = data.val().paramedico;
        var cell6 = row.insertCell(6);
        cell6.innerHTML = data.val().fecha;
        var cell7 = row.insertCell(7);
        cell7.innerHTML = data.val().sintomas;
        var cell8 = row.insertCell(8);
        cell8.innerHTML = data.val().diagnostico;
        var cell9 = row.insertCell(9);
        cell9.innerHTML = data.val().condicionVital;
        var cell10 = row.insertCell(10);
        cell10.innerHTML = data.val().riesgo;
        var cel11 = row.insertCell(11);
        cel11.innerHTML = '<a class="waves-effect waves-light btn">Finalizar</a>';
});



