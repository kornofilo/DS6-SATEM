package com.example.r_2g_.satemapp;

import java.security.Timestamp;

class Emergencias {
    public int id;
    public String nombre;
    public String Cedula;
    public int numAmbulancia;
    public String lugarAccidente;
    public String paramedico;
    public String  fecha;
    public String sintomas;
    public String diagnostico;
    public String condicionVital;
    public String riesgo;

    public Emergencias() {
    }

    public Emergencias(int id, String nombre, String cedula, int numAmbulancia, String lugarAccidente, String paramedico, String fecha, String sintomas, String diagnostico, String condicionVital, String riesgo) {
        this.id = id;
        this.nombre = nombre;
        Cedula = cedula;
        this.numAmbulancia = numAmbulancia;
        this.lugarAccidente = lugarAccidente;
        this.paramedico = paramedico;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.condicionVital = condicionVital;
        this.riesgo = riesgo;
    }



}
