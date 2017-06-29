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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity{

    static FirebaseDatabase database;
    static DatabaseReference myRef;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static ViewPager mViewPager;
    static SharedPreferences pref;
    static boolean tema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Verificamos el tema seleccionado por el usuario
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        tema = pref.getBoolean("nightMode_switch", false);

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
        }

        if (id == R.id.action_profile) {
            intentMenu = new Intent(this, ProfileActivity.class);
            startActivity(intentMenu);
            finish();
        }

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogout);
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



                //En el case #3, cargamos el fragment que contendrá el perfil del usuario logueado.
                case 2: {
                    rootView = inflater.inflate(R.layout.fragment_historial_pacientes, container, false);
                    fillTabHistorialPacientes(rootView);


                }
                break;

                case 3: {
                    rootView = inflater.inflate(R.layout.fragment_historial, container, false);
                    fillTab2(rootView);







                }
                break;
            }//Fin Switch-Case.

            return rootView;

        }

        private void fillTabDiagnostico(final View rootView) {
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
                    if(dataSnapshot.getValue() == null || dataSnapshot.getValue().toString().equals("")){


                        emergencia.setText(R.string.occupiedOrInvalidAmbulance);
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

                        //Dependiendo del valor recuperado, se establece el tema para la activity.
                        if(tema) {
                            emergencia.setTextColor(Color.WHITE);
                        }
                        else {
                            emergencia.setTextColor(Color.BLACK);
                        }
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
                                    emergencia.setText(ds.child("suceso").getValue().toString() + " @ " + ds.child("lugarAccidente").getValue().toString());
                                    lugar.setText(ds.child("lugarAccidente").getValue().toString());
                                    suceso.setText(ds.child("suceso").getValue().toString());
                                }




                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                    }



                }

                @Override
                public void onCancelled(DatabaseError error) {

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
                    paciente.setGenero(generoV);
                    paciente.setSuceso(sucesoV);
                    paciente.setLugarAccidente(lugarV);
                    paciente.setSintomas(sintomasV);
                    paciente.setDiagnostico(diagnosticoV);
                    paciente.setCondicionVital(condicionVitalV);
                    paciente.setRiesgo(riesgoV);
                    paciente.setFecha(hourdateFormat.format(date));
                    paciente.setNumAmbulancia(miAmbulancia);
                    paciente.setIdEmergencia_numAmbulancia_paramedico(paciente.getIdEmergencia() + "_" + miAmbulancia + "_" + user.getEmail());


                    if(!nombreV.equals("") && !cedulaV.equals("") && !generoV.equals("") && !lugarV.equals("") && !sintomasV.equals("") && !diagnosticoV.equals("") && !riesgoV.equals("")){
                        String id = myRef.push().getKey();
                        pacientesReference.child(id).setValue(paciente);
                        addPacientesCount(user.getUid(), miAmbulancia);
                        Toast.makeText(getActivity(),"Emergencia Enviada Correctamente.",Toast.LENGTH_SHORT).show();

                        //Limpiamos los datos
                        nombre.setText("");
                        cedula.setText("");
                        sintomas.setText("");
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
            Query emergenciasQuery = myRef.orderByChild("ambulancia/"+ miAmbulancia).equalTo(miAmbulancia);


            emergenciasQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    TextView noHistTV;
                    noHistTV = (TextView) finalRootView1.findViewById(R.id.textViewNTU);
                    if(dataSnapshot.getValue() == null) {
                        noHistTV.setVisibility(View.VISIBLE);
                        noHistTV.setText(R.string.noHistEmergencyAmbulance);
                    }else{
                        noHistTV.setVisibility(View.INVISIBLE);
                        ListView historialLV;
                        historialLV = (ListView) finalRootView1.findViewById(R.id.listViewHistorial);
                        ArrayList<String> mylist = new ArrayList<>();
                        ArrayAdapter<String> adapter;

                        for(DataSnapshot ds : dataSnapshot.getChildren() ){
                            String resumen =
                                     "#" + ds.getKey() + "\n"
                                     + "-Suceso: " +  ds.child("suceso").getValue().toString() + "\n"
                                     + "-Lugar: " +  ds.child("lugarAccidente").getValue().toString() + "\n"
                                             + "-Fecha: " + ds.child("fechaRegistro").getValue().toString() + "\n"
                                             + "-Estado: " + ds.child("estado").getValue().toString() + "\n";

                            mylist.add(resumen);
                        }
                            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mylist);
                            historialLV.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

        }

        void fillTabHistorialPacientes(final View rootView) {
            final String miAmbulancia =  pref.getString("setAmbulancia",null);
            final ListView historialLV = (ListView) rootView.findViewById(R.id.listViewHistorial);
            DatabaseReference ambulanciaRef = database.getReference("ambulancias/" + miAmbulancia + "/emergenciaActual");
            final TextView noHistTV;
            noHistTV = (TextView) rootView.findViewById(R.id.textViewNTU);
            ambulanciaRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        final ArrayList<Paciente> misPacientes = new ArrayList<>();
                        final ArrayList<String> mylist = new ArrayList<>();
                        if (mylist.isEmpty()){
                            noHistTV.setVisibility(View.VISIBLE);
                        }
                            final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mylist);
                            historialLV.setAdapter(adapter);


                        DatabaseReference pacientesRef = database.getReference("pacientes/");
                        Query pacientesQuery = pacientesRef.orderByChild("idEmergencia_numAmbulancia_paramedico").equalTo(dataSnapshot.getValue().toString() + "_" + miAmbulancia + "_" + user.getEmail());
                        pacientesQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Paciente paciente = new Paciente();

                                    historialLV.setVisibility(View.VISIBLE);

                                    noHistTV.setVisibility(View.INVISIBLE);

                                    paciente.setNombre(dataSnapshot.child("nombre").getValue().toString());
                                    paciente.setCedula(dataSnapshot.child("cedula").getValue().toString());
                                    paciente.setGenero(dataSnapshot.child("genero").getValue().toString());
                                    paciente.setSuceso(dataSnapshot.child("suceso").getValue().toString());
                                    paciente.setLugarAccidente(dataSnapshot.child("lugarAccidente").getValue().toString());
                                    paciente.setSintomas(dataSnapshot.child("sintomas").getValue().toString());
                                    paciente.setDiagnostico(dataSnapshot.child("diagnostico").getValue().toString());
                                    paciente.setCondicionVital(dataSnapshot.child("condicionVital").getValue().toString());
                                    paciente.setFecha(dataSnapshot.child("fecha").getValue().toString());
                                    paciente.setIdEmergencia_numAmbulancia_paramedico(dataSnapshot.child("idEmergencia_numAmbulancia_paramedico").getValue().toString());
                                    paciente.setIdEmergencia(dataSnapshot.child("idEmergencia").getValue().toString());
                                    paciente.setNumAmbulancia(dataSnapshot.child("numAmbulancia").getValue().toString());
                                    paciente.setRiesgo(dataSnapshot.child("riesgo").getValue().toString());
                                    paciente.setId(dataSnapshot.getKey());
                                    misPacientes.add(paciente);


                                    String resumen = dataSnapshot.child("nombre").getValue().toString() + " (" + dataSnapshot.child("cedula").getValue().toString() + "):"
                                        + "\n\n -Fecha: " + dataSnapshot.child("fecha").getValue().toString()
                                        + "\n\n -Suceso: " + dataSnapshot.child("suceso").getValue().toString()
                                        + "\n\n -Lugar: " + dataSnapshot.child("lugarAccidente").getValue().toString()
                                        + "\n\n -Síntomas: " + dataSnapshot.child("sintomas").getValue().toString()
                                        + "\n\n -Diagnóstico: " + dataSnapshot.child("diagnostico").getValue().toString();

                                    mylist.add(resumen);
                                    adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                String resumen = dataSnapshot.child("nombre").getValue().toString() + " (" + dataSnapshot.child("cedula").getValue().toString() + "):"
                                        + "\n\n -Fecha: " + dataSnapshot.child("fecha").getValue().toString()
                                        + "\n\n -Suceso: " + dataSnapshot.child("suceso").getValue().toString()
                                        + "\n\n -Lugar: " + dataSnapshot.child("lugarAccidente").getValue().toString()
                                        + "\n\n -Síntomas: " + dataSnapshot.child("sintomas").getValue().toString()
                                        + "\n\n -Diagnóstico: " + dataSnapshot.child("diagnostico").getValue().toString();

                                mylist.remove(resumen);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        historialLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                System.out.println(misPacientes.get(position).getNombre());
                                //Al seleccionar un item del listview, llevamos al usuario a un formulario para que pueda actualizar el diagnóstico del paciente.
                                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                                intent.putExtra("nombre",misPacientes.get(position).getNombre());
                                intent.putExtra("cedula",misPacientes.get(position).getCedula());
                                intent.putExtra("genero",misPacientes.get(position).getGenero());
                                intent.putExtra("suceso",misPacientes.get(position).getSuceso());
                                intent.putExtra("lugarAccidente",misPacientes.get(position).getLugarAccidente());
                                intent.putExtra("sintomas",misPacientes.get(position).getSintomas());
                                intent.putExtra("diagnostico",misPacientes.get(position).getDiagnostico());
                                intent.putExtra("condicionVital",misPacientes.get(position).getCondicionVital());
                                intent.putExtra("riesgo",misPacientes.get(position).getRiesgo());
                                intent.putExtra("key",misPacientes.get(position).getId());
                                intent.putExtra("idEmergencia",misPacientes.get(position).getIdEmergencia());
                                intent.putExtra("idEmergencia_numAmbulancia_paramedico",misPacientes.get(position).getIdEmergencia_numAmbulancia_paramedico());
                                intent.putExtra("fecha",misPacientes.get(position).getFecha());
                                intent.putExtra("numAmbulancia",misPacientes.get(position).getNumAmbulancia());




                                startActivity(intent);
                            }
                        });


                    } else {
                        noHistTV.setVisibility(View.VISIBLE);
                        historialLV.setVisibility(View.INVISIBLE);
                        noHistTV.setText(R.string.noCurrentEmergencyAmbulance);
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }//Fin de la función


        void addPacientesCount(final String uid, final String miAmbulancia){
            final DatabaseReference userRef = database.getReference("paramedicos/" + uid);

            System.out.println(userRef.getDatabase());
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    Map userMap = new HashMap<>();
                    System.out.println("HERE");
                    int cantidadPacientes = Integer.parseInt(dataSnapshot.child("cantidadPacientes").getValue().toString());
                    cantidadPacientes += 1;
                    System.out.println(cantidadPacientes);
                    userMap.put("ambulancia",miAmbulancia);
                    userMap.put("cantidadPacientes","" +cantidadPacientes);
                    userMap.put("correo",dataSnapshot.child("correo").getValue().toString());
                    userMap.put("nombre",dataSnapshot.child("nombre").getValue().toString());
                    userMap.put("profilePic",dataSnapshot.child("profilePic").getValue().toString());
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/paramedicos/" + uid, userMap);
                    databaseReference.updateChildren(childUpdates);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
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

}//Fin de clase.
