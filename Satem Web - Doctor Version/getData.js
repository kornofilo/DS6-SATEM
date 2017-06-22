/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos a los pacientes registrados.

$(document).ready(function(){
          var dbRefObjectFinalizada = firebase.database().ref('pacientes/');
          var tablePacientes = $('#pacientesTable').DataTable( {
          "paging":   false,
          "ordering": false,
          "info":     false,
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();

          dbRefObjectFinalizada.on("child_added", function(data) {
          var dataSet = [data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo];
          tablePacientes.rows.add([dataSet]).draw();  
          
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              tablePacientes.search( $(this).val() ).draw();
           })       


      });//Fin JQuery



     



