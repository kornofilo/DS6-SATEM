 //<!-- firebase password reset script-->


 function reset() {
     console.log("im here");
     //Obtener valores del formulario de correo
    const txtEmail = document.getElementById('email');
    const btnEnviar = document.getElementById('btnEnviar');
    const email = txtEmail.value;

    var auth = firebase.auth();
    var emailAddress = email;
    console.log(emailAddress);
    auth.sendPasswordResetEmail(emailAddress).then(function () {
         // Email sent.
     }, function (error) {
         // An error happened.
     });
 };