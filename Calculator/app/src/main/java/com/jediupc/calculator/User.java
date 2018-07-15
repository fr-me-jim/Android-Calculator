package com.jediupc.calculator;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private String username;
    private String password;
    private String telefon;
    private String universitat;
    private RealmList<Puntuacion> punt;

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setUniversitat(String universitat) {
        this.universitat = universitat;
    }


    public RealmList<Puntuacion> getPunt() {
        return punt;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getUniversitat() {
        return universitat;
    }
}
