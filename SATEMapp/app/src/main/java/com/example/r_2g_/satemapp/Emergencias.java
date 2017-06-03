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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public int getNumAmbulancia() {
        return numAmbulancia;
    }

    public String getLugarAccidente() {
        return lugarAccidente;
    }

    public String getParamedico() {
        return paramedico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getCondicionVital() {
        return condicionVital;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public void setNumAmbulancia(int numAmbulancia) {
        this.numAmbulancia = numAmbulancia;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public void setParamedico(String paramedico) {
        this.paramedico = paramedico;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setCondicionVital(String condicionVital) {
        this.condicionVital = condicionVital;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
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
