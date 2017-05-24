

//Obtenemos los elementos.
var table = document.getElementById('enCaminoTable');


//Cargamos los datos de la DB de Firebase.
var dbRefObject = firebase.database().ref('emergencias/');
dbRefObject.orderByChild("estado").equalTo("En Camino").on("child_added", function(data) {
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0); 
    cell1.innerHTML = data.key;
    var cell2 = row.insertCell(1);
    cell2.innerHTML = data.val().nombre;
    var cell2 = row.insertCell(2);
    cell2.innerHTML = data.val().ced;
    var cell3 = row.insertCell(3);
    cell3.innerHTML = data.val().ambulancia;
    var cell4 = row.insertCell(4);
    cell4.innerHTML = data.val().lugar;
    var cell5 = row.insertCell(5);
    cell5.innerHTML = data.val().paramedico;
    var cell6 = row.insertCell(6);
    cell6.innerHTML = data.val().fecha;
    var cell7 = row.insertCell(7);
    cell7.innerHTML = data.val().emergencia;
    var cell8 = row.insertCell(8);
    cell8.innerHTML = data.val().condVital;
    var cell9 = row.insertCell(9);
    cell9.innerHTML = data.val().riesgo;
});
    $(table).stacktable({myClass:'stacktable small-only'});

/*
dbRefObject.on("child_added", function(child) {
  
 
  enCaminoObj = dbRefObject.child(child.key);
  enCaminoObj.on("child_added", function(child) {
    dbRefObject.orderByChild("estado").equalTo("En Camino").on("child_added", function(data) {
     var cell2 = row.insertCell(1);
     cell2.innerHTML = data.val().nombre;
     var cell3 = row.insertCell(2);
     cell3.innerHTML = data.val().ced;
    console.log("Equal to filter: " + data.val().nombre);
    });
  });
}); */



