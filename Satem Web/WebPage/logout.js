/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (logout.js)
*/

		document.getElementById("logoutbtn").addEventListener("click", logOut);

    function logOut() {
       //Cerramos la sesión.
         firebase.auth().signOut().then(function() {
          window.location = "login.html"
          }).catch(function(error) {
      
        });
    }