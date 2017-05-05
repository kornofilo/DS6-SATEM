/*  
    Desarrollo De Software VI
    Proyecto Final - SATEM
    Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda 
    Archivo:  (index.jsp)
*/
		(function() {
  			// Initialize Firebase

      //Obtenemos los elementos del formulario.
      const txtEmail = document.getElementById('email');
      const txtPassword = document.getElementById('password');
      const btnLogin = document.getElementById('btnLogin');
      var message;

      //Agregar evento de login
      btnLogin.addEventListener('click',e => {
        //Obtener valores del Login
        const email = txtEmail.value;
        const password = txtPassword.value;
        const auth = firebase.auth();
        
        //Loguearse
        auth.signInWithEmailAndPassword(email,password).catch(function(error) {
         message = error.message;
       
        });

        auth.onAuthStateChanged(user => {
          if (user) {
            window.location = "index.html";
          }
          else {
             window.alert("Contraseña o password inválido");
          }
        });
        

      }) 
	}());