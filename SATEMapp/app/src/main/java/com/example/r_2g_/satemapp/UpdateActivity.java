package com.example.r_2g_.satemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;


public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    static SharedPreferences pref;
    EditText nombreET, cedulaET,sucesoET,lugarET,sintomasET,diagnosticoET;
    Spinner generoSP,condicionVitalSP,riesgoSP;
    String pacienteKey;


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
        sucesoET = (EditText) findViewById(R.id.editTextSuceso);
        lugarET = (EditText) findViewById(R.id.editTextLugar);
        sintomasET = (EditText) findViewById(R.id.editTextSintomas);
        diagnosticoET = (EditText) findViewById(R.id.editTextDiagnostico);

        //Inicializamos los Spinners del formulario
        generoSP = (Spinner) findViewById(R.id.spinnerSexo);
        condicionVitalSP = (Spinner) findViewById(R.id.spinnerCondVital);
        riesgoSP = (Spinner) findViewById(R.id.spinnerRiesgo);





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
            //Sexo
            generoSP.setSelection(getSelectedGenero(extras.getString("genero")));
            //Suceso
            sucesoET.setText(extras.getString("suceso"));
            //Lugar
            lugarET.setText(extras.getString("lugarAccidente"));
            //Síntomas
            sintomasET.setText(extras.getString("sintomas"));
            //Diagnóstico
            diagnosticoET.setText(extras.getString("diagnostico"));
            //Condición Vital
            condicionVitalSP.setSelection(getSelectedCondVital(extras.getString("condicionVital")));
            //Riesgo
            riesgoSP.setSelection(getSelectedRiesgo(extras.getString("riesgo")));

            //Obtenemos el key del nodo del paciente:
             pacienteKey = extras.getString("key");



        }
    }

    @Override
    public void onClick(View v) {
        if(!nombreET.getText().toString().equals("") && !cedulaET.getText().toString().equals("") && !generoSP.getSelectedItem().toString().equals("") && !lugarET.getText().toString().equals("") && !sintomasET.getText().toString().equals("") && !diagnosticoET.getText().toString().equals("") && !riesgoSP.getSelectedItem().toString().equals("")){
            DatabaseReference pacienteRef = FirebaseDatabase.getInstance().getReference();
            pacienteRef.child("pacientes/" + pacienteKey).runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    mutableData.child("nombre").setValue(nombreET.getText().toString());
                    mutableData.child("cedula").setValue(cedulaET.getText().toString());
                    mutableData.child("genero").setValue(generoSP.getSelectedItem().toString());
                    mutableData.child("suceso").setValue(sucesoET.getText().toString());
                    mutableData.child("lugarAccidente").setValue(lugarET.getText().toString());
                    mutableData.child("sintomas").setValue(sintomasET.getText().toString());
                    mutableData.child("diagnostico").setValue(diagnosticoET.getText().toString());
                    mutableData.child("condicionVital").setValue(condicionVitalSP.getSelectedItem().toString());
                    mutableData.child("riesgo").setValue(riesgoSP.getSelectedItem().toString());








                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);



                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }

    }

    /*Las funciones getSelectedGenero,getSelectedCondVital y getSelectedRiesgo busca los valores seleccionados por el usuario al registrar un diagnóstico
     en las secciones de Género,Condición Vital y Riesgo en los arrays que alimentan a los a sus determinados spinners y devuelve la posición de ese valor
     para que al ingresar al formulario de actualización se muestre el valor seleccionado anteriormente por el usuario.
    */
    int getSelectedGenero(String valor){
        int posicion = 0;

        String[] genero = getResources().getStringArray(R.array.sexoOpts);
        for(int i=0; i < genero.length; i++){
            if(genero[i].equals(valor)){
                posicion = i;
            }
        }
        return posicion;
    }

    int getSelectedCondVital(String valor){
        int posicion = 0;

        String[] condVital = getResources().getStringArray(R.array.condVitalOpts);
        for(int i=0; i < condVital.length; i++){
            if(condVital[i].equals(valor)){
                posicion = i;
            }
        }
        return posicion;
    }

    int getSelectedRiesgo(String valor){
        int posicion = 0;

        String[] riesgo = getResources().getStringArray(R.array.riesgoOpts);
        for(int i=0; i < riesgo.length; i++){
            if(riesgo[i].equals(valor)){
                posicion = i;
            }
        }
        return posicion;
    }
}
