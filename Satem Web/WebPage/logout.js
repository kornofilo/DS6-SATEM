    //Script de autenticación de firebase.	
		(function() {
  	
    const logoutTab = document.getElementById('#logoutTab');

    logoutTab.addEventListener('click',e => {
        //Obtener valores del Login  	

      firebase.auth().signOut().then(function() {
      window.location = "login.html"
      }).catch(function(error) {
      // An error happened.
      });
    });
	}());