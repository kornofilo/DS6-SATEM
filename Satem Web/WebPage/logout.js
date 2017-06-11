/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (logout.js)
*/

    function logOut() {
       //Cerramos la sesión.
         firebase.auth().signOut().then(function() {
          window.location = "login2.0.html"
          }).catch(function(error) {
      
        });
    }