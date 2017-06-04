package com.example.r_2g_.satemapp;


class Emergencias {
    public String nombre;
    public String cedula;
    public String numAmbulancia;
    public String lugarAccidente;
    public String genero;
    public String paramedico;
    public String  fecha;
    public String sintomas;
    public String diagnostico;
    public String condicionVital;
    public String riesgo;
    public String estado;



    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNumAmbulancia() {
        return numAmbulancia;
    }

    public String getLugarAccidente() {
        return lugarAccidente;
    }

    public String getGenero() {
        return genero;
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

    public String getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNumAmbulancia(String numAmbulancia) {
        this.numAmbulancia = numAmbulancia;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Emergencias(String nombre, String cedula, String numAmbulancia, String lugarAccidente, String genero, String paramedico, String fecha, String sintomas, String diagnostico, String condicionVital, String riesgo, String estado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.numAmbulancia = numAmbulancia;
        this.lugarAccidente = lugarAccidente;
        this.genero = genero;
        this.paramedico = paramedico;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.condicionVital = condicionVital;
        this.riesgo = riesgo;
        this.estado = estado;
    }
}
