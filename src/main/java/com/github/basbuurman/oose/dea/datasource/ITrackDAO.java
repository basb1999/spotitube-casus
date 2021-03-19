package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import java.util.List;

public interface ITrackDAO {
    List<TrackDTO> getAllTracks(int playlistId, String token);
}