
package com.jediupc.calculator;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPuntuaciones {

    @SerializedName("puntuaciones")
    @Expose
    private List<com.jediupc.calculator.Puntuaciones> puntuaciones = null;

    public List<com.jediupc.calculator.Puntuaciones> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<com.jediupc.calculator.Puntuaciones> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

}
