package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.ITrackDAO;
import com.github.basbuurman.oose.dea.resources.dto.TracksDTO;

import javax.inject.Inject;

public class TrackService implements ITrackService {
    private ITrackDAO trackDAO;

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksDTO getAllTracks(int playlistId, String token) {
        return new TracksDTO(trackDAO.getAllTracks(playlistId, token));
    }
}