package com.example.r_2g_.satemapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user;
    TextView profileText;
    ImageView profilePicture;
    SharedPreferences pref;
    String miAmbulancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profileText = (TextView) findViewById(R.id.profileBody);
        profilePicture = (ImageView) findViewById(R.id.profilePicture);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        miAmbulancia =  pref.getString("setAmbulancia",null);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            setTitle(user.getEmail());
        }

        //El método setProfile, consulta los datos del usuario logueado en la DB y los muestra en el layout
        setProfile();
    }

    void setProfile(){
        final DatabaseReference ambulanceRef = FirebaseDatabase.getInstance().getReference();
        ambulanceRef.child("paramedicos").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                profileText.setText("Nombre: " + dataSnapshot.child("nombre").getValue().toString() + "\n\n" +
                        "Ambulancia actual: " + miAmbulancia

                );
                Picasso.with(getBaseContext()).load(dataSnapshot.child("profilePic").getValue().toString()).into(profilePicture);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
