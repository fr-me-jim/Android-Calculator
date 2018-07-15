package com.jediupc.calculator;

import io.realm.RealmObject;

public class Puntuacion extends RealmObject {
    private String fecha;
    private int puntuacion;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}

