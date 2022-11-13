package edu.upc.dsa;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

public class PartidaManagerImpl implements PartidaManager{
    List<Juego> listaJuegos;
    List<Partida> listaPartidas;
    List<Usuario> listaUsuarios;
    private static PartidaManagerImpl instance;


    public static PartidaManagerImpl getInstance(){
        //logger.info(instance);
        if(instance == null)
            instance = new PartidaManagerImpl();
        //logger.info(instance);
        return instance;
    }

    final static Logger logger = Logger.getLogger(PartidaManagerImpl.class);

    public PartidaManagerImpl() {
        this.listaJuegos = new ArrayList<>();
        this.listaPartidas = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }
    public int partidaSize(){
        int s = listaPartidas.size();
        return s;
    }
    public Juego getJuego(String id) {
        for (Juego j: this.listaJuegos) {
            if (j.getId().equals(id)) {
                return j;
            }
        }
        logger.warn("not found " + id);
        return null;
    }
    public Usuario getUsuario(String id) {
        for (Usuario u: this.listaUsuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        logger.warn("not found " + id);
        return null;
    }
    public Partida getPartidaPorUsuario(String id) {
        for (Partida p: this.listaPartidas) {
            if (p.getIdUsuario().equals(id)) {
                return p;
            }
        }
        logger.warn("not found " + id);
        return null;
    }
    @Override
    public Juego crearJuego(String id, String descrip, int numNiveles) {
        Juego juego = new Juego(id, descrip, numNiveles);
        listaJuegos.add(juego);
        return juego;
    }
    public Usuario crearUsuario(String id, String nom) {
        Usuario usuario = new Usuario(id, nom);
        listaUsuarios.add(usuario);
        return usuario;
    }

    @Override
    public void iniciarPartida(String idJuego, String idUsuario) {
        Juego juego = getJuego(idJuego);
        Usuario usuario = getUsuario(idUsuario);
        if (juego!=null && usuario!=null) {
            if(idUsuario != getPartidaPorUsuario(idUsuario).getIdUsuario()){
                Partida partida = new Partida(idJuego, idUsuario,1,50);
            }
        }
    }

    @Override
    public void nivelActual(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        Partida partida = getPartidaPorUsuario(idUsuario);
        if (partida!=null && usuario!=null) {
            int nivel = partida.getNivel();
            logger.info("Nivel:"+nivel);
        }
    }

    @Override
    public void puntuacionActual(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        Partida partida = getPartidaPorUsuario(idUsuario);
        if (partida!=null && usuario!=null) {
            int puntuacion = partida.getPuntos();
            logger.info("Puntuación:"+puntuacion);
        }
    }


    @Override
    public void pasarNivel(String idUsuario, int puntos, String fecha) {
        Usuario usuario = getUsuario(idUsuario);
        Partida partida = getPartidaPorUsuario(idUsuario);
        Juego juego = getJuego(partida.getIdJuego());
        if (partida!=null && usuario!=null) {
            if ( partida.getNivel() >= juego.getNumNiveles()){
                partida.setPuntos(partida.getPuntos()+100);
                finalizarPartida(idUsuario);
            }else{
                partida.setNivel(partida.getNivel()+1);
                partida.setPuntos(partida.getPuntos()+puntos);
                partida.setFecha(fecha);
            }
        }
    }

    @Override
    public void finalizarPartida(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        Partida partida = getPartidaPorUsuario(idUsuario);
        if (partida!=null && usuario!=null) {
            logger.info("Se ha acabado la partida");
        }
    }

    @Override
    public List<Usuario> participantesByPuntuacion(String idJuego) {
        List<Usuario> listParticipantes = null;
        for (Partida p: this.listaPartidas) {
            if (p.getIdJuego().equals(idJuego)) {
                    Usuario usuario = getUsuario(p.getIdUsuario());
                    usuario.setPuntuacionTemporal(p.getPuntos());
                    listParticipantes.add(usuario);
            }
        }
        if (listParticipantes == null){
            logger.warn("not found " + idJuego);
        }
        listParticipantes.sort((p1,p2)->Integer.compare(p1.getPuntuacionTemporal(), p2.getPuntuacionTemporal()));
        return listParticipantes;
    }

    @Override
    public List<Partida> partidasParticipadasUsuario(String idUsuario) {
        List<Partida> listParticipadas = null;
        for (Partida p: this.listaPartidas) {
            if (p.getIdUsuario().equals(idUsuario)) {
                listParticipadas.add(p);
            }
        }
        if (listParticipadas == null){
            logger.warn("not found " + idUsuario);
        }
        return listParticipadas;
    }

    @Override
    public void actividadUsuario(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        Partida partida = getPartidaPorUsuario(idUsuario);
        if (partida!=null && usuario!=null) {
            String text = "Actividad("+usuario.getNom()+", "+partida.getIdJuego()+"):->[";
            for (Partida p: this.listaPartidas) {
                if (p.getIdUsuario().equals(idUsuario)) {
                    text = text + "{level:"+partida.getNivel()+", points:"+partida.getPuntos()+", date:"+partida.getFecha()+"}";
                }
            }
            logger.warn(text+"]");
        }
        logger.warn("not found " + idUsuario);
    }

}
