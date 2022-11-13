package edu.upc.dsa.models;

public class Partida {

    String idUsuario;
    String idJuego;
    int nivel;
    int puntos;

    String fecha;
    public Partida (String idJuego, String idUsuario, int nivel, int puntos){
        this.idJuego = idJuego;
        this.idUsuario = idUsuario;
        this.nivel = nivel;
        this.puntos = puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }
}
