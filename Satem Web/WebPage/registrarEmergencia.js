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
        
          //Verificamos que los campos no estén vaciós.
          if( (txtLugar.value != undefined && txtLugar.value.length > 0) && (txtDescripcion.value != undefined && txtDescripcion.value.length > 0)){
            var r = confirm("¿Desea registrar la emergencia?");
            if (r == true) {
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

                     //Agregamos la nueva emergencia.
                 firebase.database().ref().update(updates,function(error) {
                      if (error) {
                      alert("Error al registrar la emergencia: " + error);
                      } else {         
                            console.log(updates);                
                            alert("La emergencia ha sido registrada exitosamente.");
                            //Nos desplazamos a la tab de emergencias registradas.
                            $(document).ready(function(){
                              $('ul.tabs').tabs('select_tab', 'test2');
                            });
                            cantidadAnalytics();

                          }
                     });     

               });
                                          
                //Limpiamos el formulario de registro de emergencias.
                 document.getElementById("myForm").reset(); 
              }//Fin if confirm.
      }else {
          alert("Por favor, rellene todos los campos.");
         }
      
          
  }//Fin de la función registrar.    

  function cantidadAnalytics(){
    var currentdate = new Date(); 
    var today = currentdate.getDate() + "-"
                      + (currentdate.getMonth()+1)  + "-" 
                      + currentdate.getFullYear();

    var dbRefEmergencyStats = firebase.database().ref('EmergencyStats/').limitToLast(1);
        dbRefEmergencyStats.on("child_added", function(data) {    
            if (today === data.val().date){
             dbRefAmbulancia = firebase.database().ref('EmergencyStats/' + data.key + '/cantidad');
                       dbRefAmbulancia.transaction(function(cantidad) {
                            cantidad = parseInt(cantidad) + 1;  
                            return cantidad; 
             });
            }else {
               var newPostKey = firebase.database().ref().child('EmergencyStats').push().key;
               firebase.database().ref('EmergencyStats' + '/' + newPostKey + '/').set({
                 date: today,
                 cantidad: 1
               });

            }



    });

    
  }




      


