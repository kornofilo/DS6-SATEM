/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos las emergencias Registradas.
var tableAsignar = document.getElementById('asignarTable');
        

          

    //Cargamos los datos de la DB de Firebase.
    var dbRefObject = firebase.database().ref('emergencias/');
    var contAsignar = 0;
    dbRefObject.orderByChild("estado").equalTo("Registrada").on("child_added", function(data) {
        contAsignar += 1;

        var row = tableAsignar.insertRow(-1);
        var cell1 = row.insertCell(0); 
        cell1.innerHTML = contAsignar;
        var cell2 = row.insertCell(1);
        cell2.innerHTML = data.val().suceso;
        var cell2 = row.insertCell(2);
        cell2.innerHTML = data.val().lugarAccidente;
        var cell3 = row.insertCell(3);
        cell3.innerHTML = data.val().fechaRegistro;
        var cell4 = row.insertCell(4);
        cell4.innerHTML =   
                            "<select class=\"browser-default\">" +
                            "<option value=\"\" disabled selected>Ambulancias Disponibles</option>" +
                                "<option value=\"1\">Option 1</option>" +
                               "<option value=\"2\">Option 2</option>"+
                                "<option value=\"3\">Option 3</option>" +
                              "</select>";
        var cell5 = row.insertCell(5);
        cell5.innerHTML = 
                            "<select class=\"browser-default\">" +
                            "<option value=\"\" disabled selected>Paramédicos Disponibles</option>" +
                                "<option value=\"1\">Option 1</option>" +
                               "<option value=\"2\">Option 2</option>"+
                                "<option value=\"3\">Option 3</option>" +
                              "</select>";
         var cell6 = row.insertCell(6);
         cell6.innerHTML = '<a class="waves-effect waves-light btn">Asignar</a>';
});


//Obtenemos las emergencias en Camino.
var table = document.getElementById('enCaminoTable');

          

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



