/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (registrarEmergencia.js)
*/

    function registrar() {
        var newPostKey;
        var updates = {};

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

          var dbRefObject = firebase.database().ref('emergencias').limitToLast(1);;
          dbRefObject.orderByKey().on("child_added", function(data) {               
               updates['/emergencias/' + (parseInt(data.key) + 1)] = postData;
          });


                firebase.database().ref().update(updates);
               location.replace("index2.0.html#test2");
                location.reload();

          
    }

        



        //Write the new post's data simultaneously in the posts list and the user's post list.
       

      


