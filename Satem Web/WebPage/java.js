window.onload =inicializar;


function inicializar() {
  var refambulancias;
  var tbodytablaambulancias;
  var ambulancias=[];
  var keyAmbulancias = [];
  var barChartData = {};
 tbodytablaambulancias=document.getElementById("tabla-ambulancias"); 
    refambulancias=firebase.database().ref('ambulancias/');

    refambulancias.on('child_added', function(snapshot) {
        keyAmbulancias.push(snapshot.key);
        ambulancias.push(snapshot.val().cantidadEmergencias);
       // console.log(snapshot.key);
        // console.log(keyAmbulancias);

          barChartData = {
        labels : keyAmbulancias,
        datasets : [
          {
            fillColor : "#FF8C00",
            strokeColor : "#ffffff",
            highlightFill: "#1864f2",
            highlightStroke: "#ffffff",
            data : ambulancias      
          }
        ]

      }   


    //console.log(barChartData);

    var ctx3 = document.getElementById("chart-area3").getContext("2d");
   window.myPie = new Chart(ctx3).Bar(barChartData, {responsive:true});

    });


   
  
 
      
 } 


  
