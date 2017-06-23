window.onload =inicializar;

var refambulancias;
var tbodytablaambulancias;
var ambulancias=[];
function inicializar() {
 tbodytablaambulancias=document.getElementById("tabla-ambulancias"); 
    refambulancias=firebase.database().ref().child("ambulancias");

    refambulancias.on("value",function(snap){
    var datos=snap.val();
    console.log(datos);
    var x=0;
    for (var key in datos) {
        ambulancias[x]= parseInt(datos[key].cantidadEmergencias);
        x++;               
    }
    });
  console.log(ambulancias);
  var barChartData = {
    labels : ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio"],
    datasets : [
      {
        fillColor : "#6b9dfa",
        strokeColor : "#ffffff",
        highlightFill: "#1864f2",
        highlightStroke: "#ffffff",
        data : ambulancias      }
    ]

  } 
    
  var ctx3 = document.getElementById("chart-area3").getContext("2d");
  window.myPie = new Chart(ctx3).Bar(barChartData, {responsive:true});
      
 } 


  
