package com.jediupc.calculator;

import io.realm.RealmObject;

public class PuntuacionRealm extends RealmObject{
    private String fecha;
    private Double puntuacion;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }
}
