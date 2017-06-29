package com.example.r_2g_.satemapp;


public class User {
    private String nombre;
    private String ambulancia;


    public User(String nombre, String ambulancia) {
        this.nombre = nombre;
        this.ambulancia = ambulancia;
    }

    public User() {

    }


    public User(String ambulancia) {
        this.ambulancia = ambulancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(String ambulancia) {
        this.ambulancia = ambulancia;
    }


}
