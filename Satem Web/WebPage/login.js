/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (login.js)
*/
    

    function login() {
        //Obtener valores del Login
        const txtEmail = document.getElementById('email');
        const txtPassword = document.getElementById('password');
        const btnLogin = document.getElementById('btnLogin');
        const email = txtEmail.value;
        const password = txtPassword.value;
        
        const auth = firebase.auth();

        //Intentamos loguearnos con los datos ingresados.
          
          //Verificamos si los datos de login son correctos.
          auth.signInWithEmailAndPassword(email,password).catch(function(error) {         
            if(error){
              if (error.code == "auth/wrong-password") 
                window.alert("El Nombre de Usuario o Contraseña ingresada son incorrectos. Inténtelo de nuevo.");
              else if(error.code == "auth/invalid-email")
                window.alert("El formato del correo electrónico ingresado es inválido. Inténtelo de nuevo.");
              else if(error.code == "auth/user-not-found")
                window.alert("El correo ingresado no se encuentra registrado en el sistema. Inténtelo de nuevo.");      

              }//Fin del if.
            });
          }//Fin de la función login.  

         //Si los datos ingresados son correctos, se redigirá al usuario a la página principal.
        firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
          window.location = "index2.0.html";
        } 
});
