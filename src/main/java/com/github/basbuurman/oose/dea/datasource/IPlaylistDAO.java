package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistDTO> getAllPlaylists(String token);

    void addPlaylist(PlaylistDTO playlistDTO, String token);

    void editPlaylist(int playlistId, PlaylistDTO playlistDTO, String token);

    void deletePlaylist(int playlistId, String token);

    List<TrackDTO> getAllTracksFromPlaylist(int playlistId, String token);

    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token);

    void removeTrackFromPlaylist(int playlistId, int trackId, String token);

    int calculateTotalDurationPlaylists();

    boolean checkToken(String token);
}