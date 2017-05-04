<!-- Script de autenticación de firebase.	 -->
		(function() {
  			// Initialize Firebase

      //Obtenemos los elementos del formulario.
      const txtEmail = document.getElementById('email');
      const txtPassword = document.getElementById('password');
      const btnLogin = document.getElementById('btnLogin');

      //Agregar evento de login
      btnLogin.addEventListener('click',e => {
        //Obtener valores del Login
        const email = txtEmail.value;
        const password = txtPassword.value;
        const auth = firebase.auth();
        
        //Loguearse
        auth.signInWithEmailAndPassword(email,password).catch(function(error) {
        //var errorCode = error.code;
        //console.log(error.errorCode);

        });

        auth.onAuthStateChanged(user => {
          if (user) {
            window.location = "index.html";

          }
          else {
             window.location = "login.html";
          }
        });
        

      })
	}());