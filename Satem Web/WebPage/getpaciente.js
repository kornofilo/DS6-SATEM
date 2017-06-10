/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos las emergencias Registradas.
var tableAsignar = document.getElementById('tabla_paciente');
        

          

    //Cargamos los datos de la DB de Firebase.
    var dbRefObject = firebase.database().ref('pacientes/');
    var contAsignar = 0;
    
    dbRefObject.orderByKey().equalTo("12346").on("child_added", function(data) {
        contAsignar += 1;
        console.log(data);

        var row = tableAsignar.insertRow(-1);

        var cell1 = row.insertCell(0); 
        cell1.innerHTML = data.key;

        var cell2 = row.insertCell(1);
        cell2.innerHTML = data.val().nombre;

        

        
       
});

