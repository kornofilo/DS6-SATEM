/*
        Desarrollo De Software VI
        Proyecto Final - SATEM
        Elaborado por: Aldair de Gracia, Ricardo Rubio, Víctor Pineda
        Archivo:  (MainActivity.java)
*/

package com.example.r_2g_.satemapp;

import android.content.Intent;
import android.content.SharedPreferences;
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

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    FirebaseDatabase database;
    static DatabaseReference myRef;
    static Bundle extras;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Verificamos el tema seleccionado por el usuario
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean tema = pref.getBoolean("nightMode_switch", false);
        System.out.println("tema es  " + tema);

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
        myRef = database.getReference("emergencias");

        //Obtenemos el #de ambulancia del paramedico
        extras = getIntent().getExtras();

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
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Query emergenciasQuery = myRef.orderByChild("paramedico").equalTo(user.getEmail());
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1: {
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    //EditTexts
                    final EditText nombre, cedula,sintomas,lugar,diagnostico;
                    nombre = (EditText) rootView.findViewById(R.id.editTextNombrePaciente);
                    cedula = (EditText) rootView.findViewById(R.id.editTextCedula);
                    lugar = (EditText) rootView.findViewById(R.id.editTextLugar);
                    sintomas = (EditText) rootView.findViewById(R.id.editTextSintomas);
                    diagnostico = (EditText) rootView.findViewById(R.id.editTextDiagnostico);

                    //Spinners
                    final Spinner genero, condicionVital;
                    genero = (Spinner) rootView.findViewById(R.id.spinnerSexo);
                    condicionVital = (Spinner) rootView.findViewById(R.id.spinnerCondVital);





                    //Extraemos los valores en los Textviews y Spinners


                    Button enviarEmergencia = (Button) rootView.findViewById(R.id.buttonEnviar);

                    enviarEmergencia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = myRef.push().getKey();
                            String nombreV = nombre.getText().toString();
                            String cedulaV = cedula.getText().toString();
                            String generoV = genero.getSelectedItem().toString();
                            String lugarV = lugar.getText().toString();
                            String sintomasV = sintomas.getText().toString();
                            String diagnosticoV = diagnostico.getText().toString();
                            String condicionVitalV = condicionVital.getSelectedItem().toString();

                            Date date = new Date();
                            DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            System.out.println("Hora y fecha: "+hourdateFormat.format(date));


                            final Emergencias emergencia = new Emergencias(nombreV,cedulaV,extras.getString("numAmbulance"),generoV,sintomasV,lugarV,user.getEmail(),hourdateFormat.format(date),sintomasV,diagnosticoV,condicionVitalV);
                            System.out.println(nombreV);
                            if(!nombreV.equals(""))
                               myRef.child(id).setValue(emergencia);
                            else
                                System.out.println("NOPE");

                        }
                    });


                    //Inicializamos la Clase Emergencias.





                }
                break;
                case 2: {
                    rootView = inflater.inflate(R.layout.fragment_historial, container, false);
                    // Leemos desde la base de datos.
                    final View finalRootView1 = rootView;
                    emergenciasQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            //Emergencias value = dataSnapshot.getValue(Emergencias.class);
                            if(dataSnapshot.getValue() == null) {
                                TextView noHistTV;
                                noHistTV = (TextView) finalRootView1.findViewById(R.id.textViewNTU);
                                System.out.println("Read " + dataSnapshot.getValue());
                                noHistTV.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                }
                break;
                //En el case #3, cargamos el fragment que contendrá el perfil del usuario logueado.
                case 3: {
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
                    return "Datos del Paciente";
                case 1:
                    return "Historial";
                case 2:
                    return "Perfil";
            }
            return null;
        }//Fin getPageTitle.
    }//Fin SectionsPagerAdapter.
}//Fin de clase.
