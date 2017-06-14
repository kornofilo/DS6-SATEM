/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos las emergencias Registradas.

document.getElementById("buscarPacienteForm")
    .addEventListener("keypress", function(event) {
    if (event.keyCode == 13) {
    event.preventDefault();

    var cedulatxt = document.getElementById('cedulaInput');

    window.location = "cedula.html";
    var tableAsignar = document.getElementById('asignarTableBody');

    //Cargamos los datos de la DB de Firebase.
    var dbRefObject = firebase.database().ref('pacientes/');
    var contAsignar = 0;
    
    dbRefObject.orderByKey().equalTo("12345").on("child_added", function(data) {
        contAsignar += 1;
        console.log(data);

        var row = tableAsignar.insertRow(-1);

        var cell1 = row.insertCell(0); 
        cell1.innerHTML = data.key;

        var cell2 = row.insertCell(1);
        cell2.innerHTML = data.val().nombre;

        

        
       
        }); 

    }

});

