package com.github.basbuurman.oose.dea.resources;

import com.github.basbuurman.oose.dea.services.IPlaylistService;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private IPlaylistService playlistService;

    @Inject
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        return Response.status(200).entity(playlistService.getAllPlaylists(token)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
        playlistService.addPlaylist(playlistDTO, token);
        return Response.status(201).entity(playlistService.getAllPlaylists(token)).build();
    }

    @PUT
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@PathParam("playlistId") int playlistId, @QueryParam("token") String token, PlaylistDTO playlistDTO) {
        playlistService.editPlaylist(playlistId, playlistDTO, token);
        return Response.status(200).entity(playlistService.getAllPlaylists(token)).build();
    }

    @DELETE
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("playlistId") int playlistId, @QueryParam("token") String token) {
        playlistService.deletePlaylist(playlistId, token);
        return Response.status(200).entity(playlistService.getAllPlaylists(token)).build();
    }

    @GET
    @Path("/{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("playlistId") int playlistId, @QueryParam("token") String token) {
        return Response.status(200).entity(playlistService.getAllTracksFromPlaylist(playlistId, token)).build();
    }

    @POST
    @Path("/{playlistId}/tracks/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("playlistId") int playlistId, @QueryParam("token") String token, TrackDTO trackDTO) {
        playlistService.addTrackToPlaylist(playlistId, trackDTO, token);
        return Response.status(201).entity(playlistService.getAllTracksFromPlaylist(playlistId, token)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        playlistService.removeTrackFromPlaylist(playlistId, trackId, token);
        return Response.status(200).entity(playlistService.getAllTracksFromPlaylist(playlistId, token)).build();
    }
}