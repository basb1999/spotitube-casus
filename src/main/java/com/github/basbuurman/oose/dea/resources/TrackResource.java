package com.github.basbuurman.oose.dea.resources;

import com.github.basbuurman.oose.dea.services.ITrackService;
import com.github.basbuurman.oose.dea.resources.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
    private ITrackService trackService;

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvailableTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        TracksDTO tracks = trackService.getAllTracks(playlistId, token);
        return Response.status(200).entity(tracks).build();
    }
}