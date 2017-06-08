/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (registrarEmergencia.js)
*/

    function registrar() {
        //Recuperamos los valores del formulario.
        var txtLugar = document.getElementById('lugar_emergencia');
        var txtDescripcion = document.getElementById('descripcion_emergencia');
        var currentdate = new Date(); 
        var datetime = currentdate.getDate() + "/"
                + (currentdate.getMonth()+1)  + "/" 
                + currentdate.getFullYear() + " a las "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds();

        //Seteamos la referencia de la DB.
        var DBref = firebase.database().ref('emergencias');
        
        //Construimos el objeto que contendrá el nuevo registro en la DB.
        var postData = {
          lugarAccidente: txtLugar.value,
          suceso : txtDescripcion.value,
          estado : "Registrada",
          fechaRegistro: datetime

        };


        var newPostKey = firebase.database().ref().child('emergencias').push().key;

        // Write the new post's data simultaneously in the posts list and the user's post list.
        var updates = {};
        updates['/emergencias/' + newPostKey] = postData;

        firebase.database().ref().update(updates);
        window.location = "index.html";

      }


