package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.resources.dto.TracksDTO;

public interface ITrackService {
    TracksDTO getAllTracks(int playlistId, String token);
}