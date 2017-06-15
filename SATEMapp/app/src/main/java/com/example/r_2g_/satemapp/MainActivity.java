/*
        Desarrollo De Software VI
        Proyecto Final - SATEM
        Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda
        Archivo:  (MainActivity.java)
*/

package com.example.r_2g_.satemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    static FirebaseDatabase database;
    static DatabaseReference myRef;
    static Bundle extras;
    static String ambulancia;



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static ViewPager mViewPager;
    static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Verificamos el tema seleccionado por el usuario
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean tema = pref.getBoolean("nightMode_switch", false);

        //Dependiendo del valor recuperado, se establece el tema para la activity.
        if(tema) {
            setTheme(R.style.AppTheme_NoActionBarDark);
        }
        else {
            setTheme(R.style.AppTheme_NoActionBar);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("emergencias/");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intentMenu;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
             intentMenu = new Intent(this, SettingsActivity.class);
            startActivity(intentMenu);
            finish();
        } else if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            intentMenu = new Intent(this, LoginActivity.class);
            startActivity(intentMenu);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        //Seteamos los fragments con su respectiva pestaña.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;



            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1: {
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    fillTabDiagnostico(rootView);

                }


                break;

                case 3: {
                    rootView = inflater.inflate(R.layout.fragment_historial, container, false);
                    fillTab2(rootView);


                    //Inicializamos la Clase Emergencias.





                }
                break;

                //En el case #3, cargamos el fragment que contendrá el perfil del usuario logueado.
                case 2: {
                    rootView = inflater.inflate(R.layout.fragment_profile, container, false);
                    //Recuperamos los datos del usuario logueado y lo seteamos a su determinado Textview.
                    if (user != null) {
                        String email = user.getEmail();
                        TextView emailTV;
                        emailTV = (TextView) rootView.findViewById(R.id.textViewEmailLog);
                        emailTV.setText(email);
                    }

            }
                break;
            }//Fin Switch-Case.

            return rootView;

        }

        private void fillTabDiagnostico(final View rootView) {
            final ArrayList<String> emergenciasData = new ArrayList<>();
            final Paciente paciente = new Paciente();
            //Extraemos los valores en los Textviews y Spinners

            final TextView emergencia = (TextView) rootView.findViewById(R.id.textViewResumenEmergencia);
            final TextView emergenciaActualTV = (TextView) rootView.findViewById(R.id.textViewEmergencia);
            final TextView generoTV = (TextView) rootView.findViewById(R.id.textViewSexo);
            final TextView condicionVitalTV = (TextView) rootView.findViewById(R.id.textViewCondVital);
            final TextView riesgoTV = (TextView) rootView.findViewById(R.id.textViewRiesgo);



            //EditTexts
            final EditText nombre, cedula,sintomas,lugar,diagnostico, suceso;
            nombre = (EditText) rootView.findViewById(R.id.editTextNombrePaciente);
            cedula = (EditText) rootView.findViewById(R.id.editTextCedula);
            suceso = (EditText) rootView.findViewById(R.id.editTextSuceso);
            lugar = (EditText) rootView.findViewById(R.id.editTextLugar);
            sintomas = (EditText) rootView.findViewById(R.id.editTextSintomas);
            diagnostico = (EditText) rootView.findViewById(R.id.editTextDiagnostico);


            //Spinners
            final Spinner genero, condicionVital,riesgo;
            genero = (Spinner) rootView.findViewById(R.id.spinnerSexo);
            condicionVital = (Spinner) rootView.findViewById(R.id.spinnerCondVital);
            riesgo = (Spinner) rootView.findViewById(R.id.spinnerRiesgo);

            final Button enviarEmergencia = (Button) rootView.findViewById(R.id.buttonEnviar);


            //Traemos las emergencias asignadas a la ambulancia.
            final String miAmbulancia =  pref.getString("setAmbulancia",null);
            DatabaseReference ambulanciaRef = database.getReference("ambulancias/" + miAmbulancia + "/emergenciaActual");
            ambulanciaRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println( "query1 " + dataSnapshot);
                    if(dataSnapshot.getValue() == null || dataSnapshot.getValue().toString().equals("")){


                        emergencia.setText("* Atención La ambulancia seleccionada no tiene emergencias en curso o no está registrada en el sistema. *");
                        emergencia.setTextColor(Color.RED);
                        enviarEmergencia.setVisibility(View.GONE);
                        emergenciaActualTV.setVisibility(View.GONE);
                        nombre.setVisibility(View.GONE);
                        cedula.setVisibility(View.GONE);
                        generoTV.setVisibility(View.GONE);
                        genero.setVisibility(View.GONE);
                        suceso.setVisibility(View.GONE);
                        lugar.setVisibility(View.GONE);
                        sintomas.setVisibility(View.GONE);
                        diagnostico.setVisibility(View.GONE);
                        condicionVitalTV.setVisibility(View.GONE);
                        condicionVital.setVisibility(View.GONE);
                        riesgoTV.setVisibility(View.GONE);
                        riesgo.setVisibility(View.GONE);
                    }else {
                        //Si la ambulancia tiene una emergencia en proceso, mostramos el formulario de diagnóstico de pacientes.
                        emergencia.setTextColor(Color.BLACK);
                        enviarEmergencia.setVisibility(View.VISIBLE);
                        emergenciaActualTV.setVisibility(View.VISIBLE);
                        nombre.setVisibility(View.VISIBLE);
                        cedula.setVisibility(View.VISIBLE);
                        generoTV.setVisibility(View.VISIBLE);
                        genero.setVisibility(View.VISIBLE);
                        suceso.setVisibility(View.VISIBLE);
                        lugar.setVisibility(View.VISIBLE);
                        sintomas.setVisibility(View.VISIBLE);
                        diagnostico.setVisibility(View.VISIBLE);
                        condicionVitalTV.setVisibility(View.VISIBLE);
                        condicionVital.setVisibility(View.VISIBLE);
                        riesgoTV.setVisibility(View.VISIBLE);
                        riesgo.setVisibility(View.VISIBLE);

                        //Seteamos el ID de la emergencia en el diagnostico del paciente.
                        paciente.setIdEmergencia(dataSnapshot.getValue().toString());
                        Query misEmergenciaQuery2 = myRef.orderByKey().equalTo(dataSnapshot.getValue().toString());
                        misEmergenciaQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren() ){
                                    System.out.println( "query2 " + ds);
                                    emergencia.setText(ds.child("suceso").getValue().toString() + " @ " + ds.child("lugarAccidente").getValue().toString());
                                    lugar.setText(ds.child("lugarAccidente").getValue().toString());
                                    suceso.setText(ds.child("suceso").getValue().toString());
                                }




                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                System.out.println("Failed to read value." + error.toException());
                            }
                        });
                    }



                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    System.out.println("Failed to read value." + error.toException());
                }
            });





            enviarEmergencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference pacientesReference = database.getReference("pacientes");
                    String nombreV = nombre.getText().toString();
                    String cedulaV = cedula.getText().toString();
                    String generoV = genero.getSelectedItem().toString();
                    String sucesoV = suceso.getText().toString();
                    String lugarV = lugar.getText().toString();
                    String sintomasV = sintomas.getText().toString();
                    String diagnosticoV = diagnostico.getText().toString();
                    String condicionVitalV = condicionVital.getSelectedItem().toString();
                    String riesgoV = riesgo.getSelectedItem().toString();

                    Date date = new Date();
                    DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    //Llenamos el objeto Emergencias con los datos obtenidos por el usuario.
                    paciente.setNombre(nombreV);
                    paciente.setCedula(cedulaV);
                    paciente.setCedula(cedulaV);
                    paciente.setSuceso(sucesoV);
                    paciente.setLugarAccidente(lugarV);
                    paciente.setSintomas(sintomasV);
                    paciente.setDiagnostico(diagnosticoV);
                    paciente.setCondicionVital(condicionVitalV);
                    paciente.setRiesgo(riesgoV);
                    paciente.setFecha(hourdateFormat.format(date));
                    paciente.setNumAmbulancia(miAmbulancia);
                    paciente.setNumAmbulancia_paramedico(miAmbulancia + "_" + user.getEmail());


                    if(!nombreV.equals("") && !cedulaV.equals("") && !generoV.equals("") && !lugarV.equals("") && !sintomasV.equals("") && !diagnosticoV.equals("") && !riesgoV.equals("")){
                        String id = myRef.push().getKey();
                        pacientesReference.child(id).setValue(paciente);
                        Toast.makeText(getActivity(),"Emergencia Enviada Correctamente.",Toast.LENGTH_SHORT).show();

                        //Limpiamos los datos
                        nombre.setText("");
                        cedula.setText("");
                        lugar.setText("");
                        sintomas.setText("");
                        suceso.setText("");
                        diagnostico.setText("");

                        mViewPager.setCurrentItem(1, true);


                    }
                    else
                        Toast.makeText(getActivity(),"Por favor, llene todos los campos.",Toast.LENGTH_SHORT).show();

                }
            });



        }



        void fillTab2(View rootView) {

            final View finalRootView1 = rootView;
            String miAmbulancia =  pref.getString("setAmbulancia",null);
            System.out.println(miAmbulancia);
            Query emergenciasQuery = myRef.orderByChild("numAmbulancia").equalTo(miAmbulancia);
            emergenciasQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //Emergencias value = dataSnapshot.getValue(Emergencias.class);
                    TextView noHistTV;
                    noHistTV = (TextView) finalRootView1.findViewById(R.id.textViewNTU);
                    if(dataSnapshot.getValue() == null) {
                        noHistTV.setVisibility(View.VISIBLE);
                    }else{
                        noHistTV.setVisibility(View.INVISIBLE);
                        ListView historialLV;
                        historialLV = (ListView) finalRootView1.findViewById(R.id.listViewHistorial);
                        ArrayList<String> mylist = new ArrayList<>();
                        ArrayAdapter<String> adapter;

                        for(DataSnapshot ds : dataSnapshot.getChildren() ){
                            System.out.println(ds);
                            String resumen =
                                     "#" + ds.getKey() + "\n"
                                     + "-Suceso: " +  ds.child("suceso").getValue().toString() + "\n"
                                     + "-Lugar: " +  ds.child("lugarAccidente").getValue().toString() + "\n"
                                             + "-Fecha: " + ds.child("fechaRegistro").getValue().toString() + "\n"
                                             + "-Estado: " + ds.child("estado").getValue().toString() + "\n";

                            mylist.add(resumen);

                        }
                        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,mylist);
                        historialLV.setAdapter(adapter);
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    System.out.println("Failed to read value." + error.toException());
                }
            });

        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        //Setemos la cantidad de pestañas que tendrá la app.
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        //Setemos los nombres de las pestañas.
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Diagnóstico del Paciente";
                case 1:
                    return "Pacientes de la Emergencia";
                case 2:
                    return "Historial de Emergencias";
            }
            return null;
        }//Fin getPageTitle.
    }//Fin SectionsPagerAdapter.

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}//Fin de clase.
