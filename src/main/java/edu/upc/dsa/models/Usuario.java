package edu.upc.dsa.models;

public class Usuario {
    String id;
    String nom;
    int puntuacionTemporal;
    public Usuario (String id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public int getPuntuacionTemporal() {
        return puntuacionTemporal;
    }

    public void setPuntuacionTemporal(int puntuacionTemporal) {
        this.puntuacionTemporal = puntuacionTemporal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
