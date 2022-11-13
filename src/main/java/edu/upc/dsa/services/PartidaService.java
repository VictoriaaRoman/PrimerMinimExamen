package edu.upc.dsa.services;
import edu.upc.dsa.PartidaManager;
import edu.upc.dsa.PartidaManagerImpl;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")

public class PartidaService {
    private PartidaManager pm;

    public PartidaService() {
        this.pm = PartidaManagerImpl.getInstance();
        if (pm.partidaSize()==0) {
            this.pm.crearJuego("1a1a", "comecocos", 5);
            this.pm.crearUsuario("p1p1","pepe");
            this.pm.iniciarPartida("1a1a","p1p1");

            this.pm.crearJuego("2a2a", "sudoku", 10);
            this.pm.crearUsuario("p2p2","maria");
            this.pm.iniciarPartida("2a2a","p2p2");

        }
    }
    @GET
    @ApiOperation(value = "get nivel actual", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNivelActual(@PathParam("id") String id) {
        pm.nivelActual(id);
        return Response.status(201).build()  ;
    }

    @GET
    @ApiOperation(value = "get puntuación actual", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response puntuacionActual (@PathParam("id") String id) {
        pm.puntuacionActual(id);
        return Response.status(201).build()  ;
    }

    @GET
    @ApiOperation(value = "get pasar de nivel", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/{id}/{puntos}/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response passarNivel (@PathParam("id") String id,@PathParam("puntos") int puntos, @PathParam("fecha") String fecha) {
        pm.pasarNivel(id,puntos,fecha);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get participantes por puntuación", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response participantesByPuntuacion(@PathParam("id") String id) {

        List<Usuario> participantes = this.pm.participantesByPuntuacion(id);
        if (participantes != null){
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(participantes) {};
            return Response.status(201).entity(entity).build()  ;
        } else return Response.status(404).build()  ;
    }

    @GET
    @ApiOperation(value = "get partidas participadas de usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partidasParticipadasUsuario(@PathParam("id") String id) {

        List<Partida> participadas = this.pm.partidasParticipadasUsuario(id);
        if (participadas != null){
            GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(participadas) {};
            return Response.status(201).entity(entity).build()  ;
        } else return Response.status(404).build()  ;
    }

    @GET
    @ApiOperation(value = "actividad de usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actividadUsuario(@PathParam("id") String id) {
        pm.actividadUsuario(id);
        return Response.status(201).build()  ;
    }
}
