/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (registrarEmergencia.js)
*/
    var form = document.getElementById("registrarForm");
    form.addEventListener("submit", registrar);

    function registrar() {
      
        var txtLugar = document.getElementById('lugar_emergencia');
        var txtDescripcion = document.getElementById('descripcion_emergencia');
        var btnRegistrar = document.getElementById('btnRegistrar');


        var DBref = firebase.database().ref('emergencias');
        
        var postData = {
          lugarAccidente: txtLugar.value,
          suceso : txtDescripcion.value,
          estado : "Registrada"

        };


        var newPostKey = firebase.database().ref().child('posts').push().key;

        // Write the new post's data simultaneously in the posts list and the user's post list.
        var updates = {};
        updates['/emergencias/' + newPostKey] = postData;

        firebase.database().ref().update(updates);
        window.location = "index.html";

      }


