/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (registrarEmergencia.js)
*/

    function registrar() {
        var updates = {};



          //Recuperamos los valores del formulario.
          var txtLugar = document.getElementById('lugar_emergencia');
          var txtDescripcion = document.getElementById('descripcion_emergencia');

        var r = confirm("¿Desea registrar la emergencia?");
          if (r == true) {
          //Verificamos que los campos no estén vaciós.
          if(txtLugar.value != undefined && txtDescripcion.value != undefined){
            var currentdate = new Date(); 
            var datetime = currentdate.getDate() + "/"
                    + (currentdate.getMonth()+1)  + "/" 
                    + currentdate.getFullYear() + " a las "  
                    + currentdate.getHours() + ":"  
                    + currentdate.getMinutes() + ":" 
                    + currentdate.getSeconds();

            
           //Construimos el objeto que contendrá el nuevo registro en la DB.
            var postData = {
              lugarAccidente: txtLugar.value,
              suceso : txtDescripcion.value,
              estado : "Registrada",
              fechaRegistro: datetime
            };


              //Recuperamos el valor más alto de los padres de las emergencias y le asignamos su  valor + 1 para la nueva emergencia.
              var dbRefObject = firebase.database().ref('emergencias/').limitToLast(1);
              dbRefObject.orderByKey().once("child_added", function(data) {               
                   updates['/emergencias/' + (parseInt(data.key) + 1)] = postData;
                    console.log(updates);



              });


                //Agregamos la nueva emergencia.
               firebase.database().ref().update(updates,function(error) {
                    if (error) {
                    alert("Error al registrar la emergencia: " + error);
                    } else {
                        alert("La emergencia ha sido registrada exitosamente.");
                        console.log(firebase.database().ref().update(updates));
                    }
               });
                //Nos desplazamos a la tab de emergencias registradas.
                 $(document).ready(function(){
                  $('ul.tabs').tabs('select_tab', 'test2');
                 });


              //Limpiamos el formulario de registro de emergencias.
               document.getElementById("myForm").reset(); 

      }else {
          alert("Por favor, rellene todos los campos.");
         }
      }//Fin if confirm.
          
    }//Fin de la función registrar.    

      


