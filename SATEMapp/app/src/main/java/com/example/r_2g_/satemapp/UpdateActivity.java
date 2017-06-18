package com.example.r_2g_.satemapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class UpdateActivity extends AppCompatActivity {
    static SharedPreferences pref;
    EditText nombreET, cedulaET,sucesoET;
    Spinner generoSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Verificamos el tema seleccionado por el usuario
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean tema = pref.getBoolean("nightMode_switch", false);

        //Dependiendo del valor recuperado, se establece el tema para la activity.
        if(tema) {
            setTheme(R.style.AppTheme_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_update);

        //Inicializamos los EditTexts del formulario
        nombreET = (EditText) findViewById(R.id.editTextNombrePaciente);
        cedulaET = (EditText) findViewById(R.id.editTextCedula);
        sucesoET= (EditText) findViewById(R.id.editTextSuceso);



        /*El método cargarDiagnostico inserta en sus respectivos EditTexts los datos del diagnóstico
          ingresados por el paramédico */
        cargarDiagnostico();
    }

    void  cargarDiagnostico(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            //Nombre
            nombreET.setText(extras.getString("nombre"));
            //Cédula
            cedulaET.setText(extras.getString("cedula"));
            //Suceso
            sucesoET.setText(extras.getString("suceso"));


        }
    }
}
