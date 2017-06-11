/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (asignarEmergencia.js)
*/


    function asignarEmergencia() {
       var updates = {};
       var selectEmergency = document.getElementById("selectEmergencia");
       var values = $('#selectAmbulancias').val();

       

       //Seteamos el estado de las ambulancias seleccionadas como ocupadas.
       for(var i=0;i < values.length; i++){
        //Recuperamos las emergencias seleccionadas en el formulario.
          var dbRefObject = firebase.database().ref('ambulancias/' + values[i])
          console.log(values[i]);
              
           
           dbRefObject.update({
   				estado: "Ocupada",
   				ultimaEmergencia: selectEmergency.value
			});
        }


         //Nos desplazamos a la tab de emergencias en camino.
         $(document).ready(function(){
              $('ul.tabs').tabs('select_tab', 'test3');
             });
     }