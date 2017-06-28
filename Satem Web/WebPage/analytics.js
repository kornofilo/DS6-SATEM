window.onload =inicializar;


function inicializar() {
  var refambulancias;
  var tbodytablaambulancias;
  var ambulancias=[];
  var keyAmbulancias = [];
  var barChartData = {};
    refambulancias=firebase.database().ref('ambulancias/');

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





   
  
 
      
 } 


  
