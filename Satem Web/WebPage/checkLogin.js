/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (checkLogin.js)
*/

        //Comprobamos si el usuario se encuentra logueado.
        firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
        } else {
          alert("Debe Iniciar Sesión para poder acceder a esta sección.");
          window.location = "login2.0.html";
        }
      });