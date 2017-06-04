/*
        Desarrollo De Software VI
        Proyecto Final - SATEM
        Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda
        Archivo:  (LoginActivity.java)
*/


package com.example.r_2g_.satemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView emailET, passwordET, ambulanceET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //Verificamos el tema seleccionado por el usuario
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean tema = pref.getBoolean("nightMode_switch", true);

        //Dependiendo del valor recuperado, se establece el tema para la activity.
        if(tema) {
            setTheme(R.style.AppTheme_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_login);
        //Inicializamos los Editext
        emailET = (EditText) findViewById(R.id.editTextUsuario);
        passwordET = (EditText) findViewById(R.id.editTextPassword);
        ambulanceET = (EditText) findViewById(R.id.editTextNAmbulancia);
        mAuth = FirebaseAuth.getInstance();

        //Si el usuario ya está logueado, lo redirigimos al la activity principal.
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    System.out.print("onAuthStateChanged:signed_in:" + user.getUid());
                    Intent itentMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(itentMain);
                } else {
                    // User is signed out
                    System.out.print("onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (v.getId()){
            case R.id.buttonLogin:{
                if(!emailET.getText().toString().equals("") && !passwordET.getText().toString().equals("")){
                    String emailV = emailET.getText().toString();
                    String passwordV = passwordET.getText().toString();
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("numAmbulance",ambulanceET.getText().toString());
                    mAuth.signInWithEmailAndPassword(emailV, passwordV)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    System.out.print("signInWithEmail:onComplete:");

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        System.out.print("signInWithEmail:failed");
                                        Toast.makeText(LoginActivity.this, "El Nombre de Usuario o Contraseña ingresada son incorrectos. Inténtelo de nuevo.",
                                                Toast.LENGTH_SHORT).show();
                                    }else if (task.isSuccessful()){
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                }
                break;


            }
        }
    }


}
