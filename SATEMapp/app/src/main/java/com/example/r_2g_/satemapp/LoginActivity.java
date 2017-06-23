/*
        Desarrollo De Software VI
        Proyecto Final - SATEM
        Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda
        Archivo:  (LoginActivity.java)
*/


package com.example.r_2g_.satemapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView emailET, passwordET, ambulanceET;
    SharedPreferences pref;
    boolean role = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //Verificamos el tema seleccionado por el usuario
        pref  = PreferenceManager.getDefaultSharedPreferences(this);
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
                    System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
                    Intent itentMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(itentMain);
                } else {
                    // User is signed out
                    System.out.println("onAuthStateChanged:signed_out");
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

    @Override
    public void onClick(View v) {
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarLogin);
        pb.setVisibility(View.VISIBLE);
        switch (v.getId()){
            case R.id.buttonLogin:{

                if(!emailET.getText().toString().equals("") && !passwordET.getText().toString().equals("") && !ambulanceET.getText().toString().equals("")){
                    String emailV = emailET.getText().toString();
                    String passwordV = passwordET.getText().toString();
                    checkRole(emailV,passwordV);
                }
                break;


            }
            case R.id.buttonForget:{
                Intent intent = new Intent(LoginActivity.this,passwordReset.class);
                startActivity(intent);
                finish();
            }
        }
    }//Fin Onclick

    //Almacenamos en el perfil del
    void setAmbulanceNumber(final String ambulancia){


        final FirebaseUser userNow = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ambulanceRef = FirebaseDatabase.getInstance().getReference();
        ambulanceRef.child("paramedicos").child(userNow.getUid()).child("ambulancia").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                mutableData.setValue(ambulancia);
                //Seteamos el numero de ambulancia como SharedPreference
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("setAmbulancia",ambulancia);
                editor.apply();
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }

    });
    }//Fin setAmbulanceNumber

    void checkRole(final String email, final String password){
        DatabaseReference paramedicoRef = FirebaseDatabase.getInstance().getReference("paramedicos/");
        final boolean[] role = new boolean[1];

        System.out.println("BEFORE " + role[0]);


        paramedicoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.child("correo").getValue().toString());
                    if (email.equals(ds.child("correo").getValue().toString())) {
                        System.out.println("ENCONTRADO");
                        login(email,password);
                        role[0] = true;
                    }
                    System.out.println("MEANWHILE " + role[0]);
                }

                if (!role[0]) {

                    Toast.makeText(LoginActivity.this, "El correo ingresado no está registrado en el sistema. Inténtelo de nuevo.",
                            Toast.LENGTH_SHORT).show();                }



            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }//Fin de la función login.

     public void login(String email, String password){
         mAuth.signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         // If sign in fails, display a message to the user. If sign in succeeds
                         // the auth state listener will be notified and logic to handle the
                         // signed in user can be handled in the listener.
                         if (!task.isSuccessful()) {
                             Toast.makeText(LoginActivity.this, "El Nombre de Usuario o Contraseña ingresada son incorrectos. Inténtelo de nuevo.",
                                     Toast.LENGTH_SHORT).show();
                             role = false;
                         }else if (task.isSuccessful()){
                             setAmbulanceNumber(ambulanceET.getText().toString());
                             Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                             startActivity(intent);
                             finish();
                         }

                     }
                 });
     }
    }
