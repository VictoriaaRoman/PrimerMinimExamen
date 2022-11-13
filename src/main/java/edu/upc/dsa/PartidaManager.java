package edu.upc.dsa;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface PartidaManager {

    public Juego crearJuego(String id, String descrip, int numNiveles);
    public void iniciarPartida(String idJuego, String idUsuario);
    public void nivelActual(String idUsuario);
    public void puntuacionActual(String idUsuario);
    public void pasarNivel(String idUsuario, int puntos, String fecha);
    public void finalizarPartida(String idUsuario);
    public List<Usuario> participantesByPuntuacion(String idJuego);
    public List<Partida> partidasParticipadasUsuario(String idUsuario);

    int partidaSize();
    public Usuario crearUsuario(String id, String nom);
    public void actividadUsuario (String idUsuario);
}
