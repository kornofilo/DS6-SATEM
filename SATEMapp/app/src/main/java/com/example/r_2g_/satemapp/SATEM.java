package com.example.r_2g_.satemapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class SATEM extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }


}
