package com.example.r_2g_.satemapp;

import java.util.HashMap;
import java.util.Map;

public class Paciente {
    public String id;
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
    public String presion;
    public String diagnostico;
    public String condicionVital;
    public String riesgo;
    public String estado;

    public Paciente() {
    }

    public Paciente(String id, String nombre, String cedula, String numAmbulancia, String lugarAccidente, String idEmergencia, String suceso, String idEmergencia_numAmbulancia_paramedico, String genero, String paramedico, String fecha, String sintomas, String presion, String diagnostico, String condicionVital, String riesgo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.numAmbulancia = numAmbulancia;
        this.lugarAccidente = lugarAccidente;
        this.idEmergencia = idEmergencia;
        this.suceso = suceso;
        this.idEmergencia_numAmbulancia_paramedico = idEmergencia_numAmbulancia_paramedico;
        this.genero = genero;
        this.paramedico = paramedico;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.presion = presion;
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

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getGenero() {
        return genero;
    }

    public String getNumAmbulancia() {
        return numAmbulancia;
    }

    public String getLugarAccidente() {
        return lugarAccidente;
    }

    public String getIdEmergencia_numAmbulancia_paramedico() {
        return idEmergencia_numAmbulancia_paramedico;
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

    String getIdEmergencia() {
        return idEmergencia;
    }

    void setIdEmergencia(String idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    void setIdEmergencia_numAmbulancia_paramedico(String idEmergencia_numAmbulancia_paramedico) {
        this.idEmergencia_numAmbulancia_paramedico = idEmergencia_numAmbulancia_paramedico;
    }
    //getter y setter de test nuevo
    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    //Cuando envías bastante data a firebase como en el caso del diagnóstico.

    //Es una buena práctica enviarlo todo en un objeto
    //este hash map es como si fuera un json con los campos
    //agarra los valores, los pone en el mapa y de ahi los envia a firebase

    public Map<String,Object> toMap(){
        Map pacienteMap = new HashMap<>();
        
        pacienteMap.put("nombre",nombre);
        pacienteMap.put("cedula",cedula);
        pacienteMap.put("numAmbulancia",numAmbulancia);
        pacienteMap.put("lugarAccidente",lugarAccidente);
        pacienteMap.put("idEmergencia",idEmergencia);
        pacienteMap.put("suceso",suceso);
        pacienteMap.put("idEmergencia_numAmbulancia_paramedico",idEmergencia_numAmbulancia_paramedico);
        pacienteMap.put("genero",genero);
        pacienteMap.put("paramedico",paramedico);
        pacienteMap.put("fecha",fecha);
        pacienteMap.put("sintomas",sintomas);
        pacienteMap.put("presion",presion);//campo nuevo de test
        pacienteMap.put("diagnostico",diagnostico);
        pacienteMap.put("condicionVital",condicionVital);
        pacienteMap.put("riesgo",riesgo);
        pacienteMap.put("estado",estado);

        return pacienteMap;
    }


}
