package com.example.r_2g_.satemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;




public class passwordReset extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        Button button;
    }
    @Override
    public void onClick(View v) {

            //Inicializamos los Editext
            EditText eMail;
            eMail = (EditText) findViewById(R.id.editText);



            FirebaseAuth auth = FirebaseAuth.getInstance();
            String emailAddress = eMail.getText().toString();


            auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(passwordReset.this, "Email de reset Enviado",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
