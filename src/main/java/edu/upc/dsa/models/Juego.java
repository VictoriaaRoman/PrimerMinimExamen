package edu.upc.dsa.models;


public class Juego {
    String id;
    String descripcion;
    int numNiveles;

    public Juego (String id, String descripcion, int nivel){
        this.id = id;
        this.descripcion = descripcion;
        this.numNiveles = nivel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumNiveles(int nivel) {
        this.numNiveles = nivel;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNumNiveles() {
        return numNiveles;
    }
}
