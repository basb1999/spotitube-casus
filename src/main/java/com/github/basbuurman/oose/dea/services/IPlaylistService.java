package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistsDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import com.github.basbuurman.oose.dea.resources.dto.TracksDTO;

public interface IPlaylistService {
    PlaylistsDTO getAllPlaylists(String token);

    void addPlaylist(PlaylistDTO playlistDTO, String token);

    void editPlaylist(int playlistId, PlaylistDTO playlistDTO, String token);

    void deletePlaylist(int playlistId, String token);

    TracksDTO getAllTracksFromPlaylist(int playlistId, String token);

    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token);

    void removeTrackFromPlaylist(int playlistId, int trackId, String token);

    void tokenCheck(String token);
}