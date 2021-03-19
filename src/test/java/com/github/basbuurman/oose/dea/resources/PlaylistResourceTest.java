package com.github.basbuurman.oose.dea.resources;

import com.github.basbuurman.oose.dea.datasource.PlaylistDAO;
import com.github.basbuurman.oose.dea.services.PlaylistService;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlaylistResourceTest {
    private PlaylistResource sut;
    private PlaylistService playlistService;
    private PlaylistDAO playlistDAO;

    private TokenDTO token;
    private PlaylistDTO playlist;
    private TrackDTO track;
    private int playlistId;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new PlaylistResource();
        playlistService = Mockito.mock(PlaylistService.class);
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        playlistService.setPlaylistDAO(playlistDAO);
        sut.setPlaylistService(playlistService);

        token = new TokenDTO();
        playlist = new PlaylistDTO();
        track = new TrackDTO();
        playlistId = 1;
    }

    @Test
    void testIfGetAllPlaylistsCallsGetAllPlaylistsOnService() {
        // Act
        sut.getAllPlaylists(token.getToken());

        // Assert
        Mockito.verify(playlistService).getAllPlaylists(token.getToken());
    }

    @Test
    void testIfGetAllPlaylistsReturns200() {
        // Act
        var playlists = sut.getAllPlaylists(token.getToken());

        // Assert
        Assertions.assertEquals(200, playlists.getStatus());
    }

    @Test
    void testIfAddPlaylistCallsAddPlaylistOnService() {
        // Act
        sut.addPlaylist(token.getToken(), playlist);

        // Assert
        Mockito.verify(playlistService).addPlaylist(playlist, token.getToken());
    }

    @Test
    void testIfAddPlaylistReturns201() {
        // Act
        var execute = sut.addPlaylist(token.getToken(), playlist);

        // Assert
        Assertions.assertEquals(201, execute.getStatus());
    }

    @Test
    void testIfEditPlaylistCallsEditPlaylistOnService() {
        // Act
        sut.editPlaylist(playlistId, token.getToken(), playlist);

        // Assert
        Mockito.verify(playlistService).editPlaylist(playlistId, playlist, token.getToken());
    }

    @Test
    void testIfEditPlaylistReturns200() {
        // Act
        var execute = sut.editPlaylist(playlistId, token.getToken(), playlist);

        // Assert
        Assertions.assertEquals(200, execute.getStatus());
    }

    @Test
    void testIfDeletePlaylistCallsDeletePlaylistOnService() {
        // Act
        sut.deletePlaylist(playlistId, token.getToken());

        // Assert
        Mockito.verify(playlistService).deletePlaylist(playlistId, token.getToken());
    }

    @Test
    void testIfDeletePlaylistReturns200() {
        // Act
        var execute = sut.deletePlaylist(playlistId, token.getToken());

        // Assert
        Assertions.assertEquals(200, execute.getStatus());
    }

    @Test
    void testIfGetAllTracksFromPlaylistCallsGetAllTracksFromPlaylistOnService() {
        // Act
        sut.getAllTracksFromPlaylist(playlistId, token.getToken());

        // Assert
        Mockito.verify(playlistService).getAllTracksFromPlaylist(playlistId, token.getToken());
    }

    @Test
    void testIfGetAllTracksFromPlaylistThrowsExceptionFromServicePassesThrough() {
        // Arrange
        int playlistId = -1;

        // Act & Assert
        Mockito.doThrow(RequestInvalidException.class).when(playlistService).getAllTracksFromPlaylist(playlistId, null);
    }

    @Test
    void testIfGetAllTracksFromPlayReturns200() {
        // Act
        var execute = sut.getAllTracksFromPlaylist(playlistId, token.getToken());

        // Assert
        Assertions.assertEquals(200, execute.getStatus());
    }

    @Test
    void testIfAddTrackToPlaylistCallsAddTrackToPlaylistOnService() {
        // Act
        sut.addTrackToPlaylist(playlistId, token.getToken(), track);

        // Assert
        Mockito.verify(playlistService).addTrackToPlaylist(playlistId, track, token.getToken());
    }

    @Test
    void testIfAddTrackToPlaylistReturns201() {
        // Act
        var execute = sut.addTrackToPlaylist(playlistId, token.getToken(), track);

        // Assert
        Assertions.assertEquals(201, execute.getStatus());
    }

    @Test
    void testIfRemoveTrackToPlaylistCallsRemoveTrackToPlaylistOnService() {
        // Act
        sut.removeTrackFromPlaylist(playlistId, track.getId() ,token.getToken());

        // Assert
        Mockito.verify(playlistService).removeTrackFromPlaylist(playlistId, track.getId() ,token.getToken());
    }

    @Test
    void testIfRemoveTrackToPlaylistReturns200() {
        // Act
        var execute = sut.removeTrackFromPlaylist(playlistId, track.getId() ,token.getToken());

        // Assert
        Assertions.assertEquals(200, execute.getStatus());
    }
}