window.onload =inicializar;


function inicializar() {

  //Ambulancias
  var refambulancias = firebase.database().ref('ambulancias/');;
  var ambulancias=[];
  var keyAmbulancias = [];

    refambulancias.on('child_added', function(snapshot) {
        keyAmbulancias.push(snapshot.key);
        ambulancias.push(snapshot.val().cantidadEmergencias);
         
      // Dibujamos el Chart
      new Chart(document.getElementById("chart-ambulancias"), {
          type: 'bar',
          data: {
            labels: keyAmbulancias,
            datasets: [
            {
              backgroundColor: "#FF8C00",
              label: "Cantidad de Emergencias",
              data: ambulancias
            }
          ]
    },
    options: {
          events: ["mouseout", "click", "touchstart", "touchmove", "touchend"],
          title: {
        display: true,
        text: 'Cantidad de Emergencias por Ambulancia'
        }
      }
    });
    });

     var ambulanciasUpdate=[];
    var keyAmbulanciasUpdate = [];

    refambulancias.on('child_changed', function(snapshot) {
        keyAmbulanciasUpdate.push(snapshot.key);
        ambulanciasUpdate.push(snapshot.val().cantidadEmergencias);
         
      // Dibujamos el Chart
      new Chart(document.getElementById("chart-ambulancias"), {
          type: 'bar',
          data: {
            labels: keyAmbulancias,
            datasets: [
            {
              backgroundColor: "#FF8C00",
              label: "Cantidad de Emergencias",
              data: ambulancias
            }
          ]
    },
    options: {
          events: ["mouseout", "click", "touchstart", "touchmove", "touchend"],
          title: {
        display: true,
        text: 'Cantidad de Emergencias por Ambulancia'
        }
      }
    });
    });

    //Paramedicos


    var refParamedicos = firebase.database().ref('paramedicos/');
    var pacientes=[];
    var keyParamedicos = [];
    var barPieData = {};
     refParamedicos.on('child_added', function(snapshot) {
        keyParamedicos.push(snapshot.val().correo);
        pacientes.push(snapshot.val().cantidadPacientes);
         
      // Dibujamos el Chart
      new Chart(document.getElementById("chart-paramedicos"), {
          type: 'pie',
          data: {
            labels: keyParamedicos,
            datasets: [
            {
              backgroundColor: ["#d5e1df","#c94c4c","#86af49","#92a8d1"],
              label: "Cantidad de Pacientes Atendidos por Paramédico",
              data: pacientes
            }
          ]
    },
    options: {
          events: ["mouseout", "click", "touchstart", "touchmove", "touchend"],
          title: {
        display: true,
        text: 'Cantidad de pacientes atendidos'
        }
      }
    });
    });


    var pacientesUpdate=[];
    var keyParamedicosUpdate = [];

     refParamedicos.on('child_changed', function(snapshot) {
        
        keyParamedicosUpdate.push(snapshot.val().correo);
        pacientesUpdate.push(snapshot.val().cantidadPacientes);
         
      // Dibujamos el Chart
      new Chart(document.getElementById("chart-paramedicos"), {
          type: 'pie',
          data: {
            labels: keyParamedicos,
            datasets: [
            {
              backgroundColor: ["#d5e1df","#c94c4c","#86af49","#92a8d1"],
              label: "Cantidad de Pacientes Atendidos por Paramédico",
              data: pacientes
            }
          ]
    },
    options: {
          events: ["mouseout", "click", "touchstart", "touchmove", "touchend"],
          title: {
        display: true,
        text: 'Cantidad de pacientes atendidos'
        }
      }
    });
    }); 





   
  
 
      
 }//Fin de la función. 


  
