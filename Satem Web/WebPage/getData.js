

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
    cell3.innerHTML = data.val().lugar;
});

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



