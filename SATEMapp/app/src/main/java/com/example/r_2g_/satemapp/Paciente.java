package com.example.r_2g_.satemapp;

/**
 * Created by wsses on 06/15/2017.
 */

public class Paciente {
    public String nombre;
    public String cedula;
    public String numAmbulancia;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNumAmbulancia() {
        return numAmbulancia;
    }

    public void setNumAmbulancia(String numAmbulancia) {
        this.numAmbulancia = numAmbulancia;
    }

    public String getLugarAccidente() {
        return lugarAccidente;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public String getSuceso() {
        return suceso;
    }

    public void setSuceso(String suceso) {
        this.suceso = suceso;
    }



    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getCondicionVital() {
        return condicionVital;
    }

    public void setCondicionVital(String condicionVital) {
        this.condicionVital = condicionVital;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdEmergencia() {
        return idEmergencia;
    }

    public void setIdEmergencia(String idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public String getIdEmergencia_numAmbulancia_paramedico() {       return idEmergencia_numAmbulancia_paramedico;
    }

    public void setIdEmergencia_numAmbulancia_paramedico(String idEmergencia_numAmbulancia_paramedico) {
        this.idEmergencia_numAmbulancia_paramedico = idEmergencia_numAmbulancia_paramedico;
    }
}
