package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.IPlaylistDAO;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistsDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import com.github.basbuurman.oose.dea.resources.dto.TracksDTO;
import com.github.basbuurman.oose.dea.services.exceptions.ForbiddenException;
import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;

import javax.inject.Inject;

public class PlaylistService implements IPlaylistService {
    private IPlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public PlaylistsDTO getAllPlaylists(String token) {
        tokenCheck(token);
        return new PlaylistsDTO(playlistDAO.getAllPlaylists(token), playlistDAO.calculateTotalDurationPlaylists());
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String token) {
        tokenCheck(token);

        if (playlistDTO == null) {
            throw new RequestInvalidException();
        }

        playlistDAO.addPlaylist(playlistDTO, token);
    }

    @Override
    public void editPlaylist(int playlistId, PlaylistDTO playlistDTO, String token) {
        tokenCheck(token);

        if (playlistId < 0 || playlistDTO == null) {
            throw new RequestInvalidException();
        }

        playlistDAO.editPlaylist(playlistId, playlistDTO, token);
    }

    @Override
    public void deletePlaylist(int playlistId, String token) {
        tokenCheck(token);

        if (playlistId < 0) {
            throw new RequestInvalidException();
        }

        playlistDAO.deletePlaylist(playlistId, token);
    }

    @Override
    public TracksDTO getAllTracksFromPlaylist(int playlistId, String token) {
        tokenCheck(token);

        if (playlistId < 0) {
            throw new RequestInvalidException();
        }

        return new TracksDTO(playlistDAO.getAllTracksFromPlaylist(playlistId, token));
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token) {
        tokenCheck(token);

        if (playlistId < 0 || trackDTO == null) {
            throw new RequestInvalidException();
        }

        playlistDAO.addTrackToPlaylist(playlistId, trackDTO, token);
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId, String token) {
        tokenCheck(token);

        if (playlistId < 0 || trackId < 0) {
            throw new RequestInvalidException();
        }

        playlistDAO.removeTrackFromPlaylist(playlistId, trackId, token);
    }

    @Override
    public void tokenCheck(String token) {
        boolean validToken = playlistDAO.checkToken(token);

        if (!validToken) {
            throw new ForbiddenException();
        }
    }
}