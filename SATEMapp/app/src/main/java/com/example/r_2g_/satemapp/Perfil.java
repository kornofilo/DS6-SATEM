package com.example.r_2g_.satemapp;


import android.net.Uri;

public class Perfil {

    String nombre;
    Uri profilePic;
    String ambulancia;

    public Perfil() {
    }

    public Perfil(String nombre, Uri profilePic, String ambulancia) {
        this.nombre = nombre;
        this.profilePic = profilePic;
        this.ambulancia = ambulancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Uri getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Uri profilePic) {
        this.profilePic = profilePic;
    }

    public String getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(String ambulancia) {
        this.ambulancia = ambulancia;
    }
}
