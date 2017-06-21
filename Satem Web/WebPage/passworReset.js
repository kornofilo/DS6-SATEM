 //<!-- firebase password reset script-->


 function reset() {
        //Obtener valores del Login
        const txtEmail = document.getElementById('email');
       
        const btnEnviar = document.getElementById('btnEnviar');
        const email = txtEmail.value;
      
        
        const auth = firebase.auth();

        <script>
            var auth = firebase.auth();
            var emailAddress = "";

            auth.sendPasswordResetEmail(emailAddress).then(function() {
            // Email sent.
            }, function(error) {
            // An error happened.
            });
        </script>