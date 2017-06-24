package com.example.r_2g_.satemapp;

/**
 * Created by wsses on 06/15/2017.
 */

public class Paciente {
    private String nombre;
    private String cedula;
    private String numAmbulancia;
    public String lugarAccidente;
    public String idEmergencia;
    public String suceso;
    public String idEmergencia_numAmbulancia_paramedico;
    public String genero;
    public String paramedico;
    public String  fecha;
    public String sintomas;
    public String diagnostico;
    public String condicionVital;
    public String riesgo;
    public String estado;

    public Paciente() {
    }

    public Paciente(String nombre, String cedula, String numAmbulancia, String lugarAccidente, String suceso, String idEmergencia_numAmbulancia_paramedico, String genero, String paramedico, String fecha, String sintomas, String diagnostico, String condicionVital, String riesgo, String estado, String idEmergencia) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.idEmergencia = idEmergencia;
        this.numAmbulancia = numAmbulancia;
        this.lugarAccidente = lugarAccidente;
        this.suceso = suceso;
        this.idEmergencia_numAmbulancia_paramedico = idEmergencia_numAmbulancia_paramedico;
        this.genero = genero;
        this.paramedico = paramedico;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.condicionVital = condicionVital;
        this.riesgo = riesgo;
        this.estado = estado;
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


    void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public String getSuceso() {
        return suceso;
    }

    void setSuceso(String suceso) {
        this.suceso = suceso;
    }

    void setGenero(String genero) {
        this.genero = genero;
    }

    public String getParamedico() {
        return paramedico;
    }

    public void setParamedico(String paramedico) {
        this.paramedico = paramedico;
    }

    public String getFecha() {
        return fecha;
    }

    void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    void setCondicionVital(String condicionVital) {
        this.condicionVital = condicionVital;
    }

    void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    String getIdEmergencia() {
        return idEmergencia;
    }

    void setIdEmergencia(String idEmergencia) {
        this.idEmergencia = idEmergencia;
    }


    void setIdEmergencia_numAmbulancia_paramedico(String idEmergencia_numAmbulancia_paramedico) {
        this.idEmergencia_numAmbulancia_paramedico = idEmergencia_numAmbulancia_paramedico;
    }
}
