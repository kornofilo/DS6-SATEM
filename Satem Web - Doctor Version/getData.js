/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (getData.js)
*/

//Obtenemos a los pacientes registrados.

$(document).ready(function(){
          var dbRefObjectPacientes = firebase.database().ref('pacientes/');
          var tablePacientes = $('#pacientesTable').DataTable( {
          "paging":   false,
          "info":     false,
           "language": {
                 "emptyTable": "No se encuentran pacientes."
              }
          } ); 

          //Eliminamos el searchbar por defecto.
          $('.dataTables_filter').remove();


          dbRefObjectPacientes.on('child_added', function(data) {    
           var rowIndex = $('#pacientesTable').dataTable().fnAddData([data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo]);
           var row = $('#pacientesTable').dataTable().fnGetNodes(rowIndex);
           $(row).attr('id', data.key);
        });


       dbRefObjectPacientes.on("child_changed", function(data) {
           $('#pacientesTable').dataTable().fnUpdate( [data.val().nombre,data.val().cedula,data.val().genero,data.val().numAmbulancia,data.val().lugarAccidente,data.val().suceso,data.val().fecha,data.val().sintomas,data.val().diagnostico,data.val().condicionVital,data.val().riesgo], document.getElementById(data.key), undefined, false, false);
        });

           //Seteamos el searchbar superior para que pueda realizar un filtrado de la tabla.
           $('#cedulaInput').keyup(function(){
              tablePacientes.search( $(this).val() ).draw();
           })       


      });//Fin JQuery


     



