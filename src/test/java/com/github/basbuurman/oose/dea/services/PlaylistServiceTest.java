package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.PlaylistDAO;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import com.github.basbuurman.oose.dea.services.exceptions.ForbiddenException;
import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlaylistServiceTest {
    private PlaylistService sut;
    private PlaylistDAO playlistDAO;

    private int playlistId;
    private int trackId;
    private TokenDTO token;
    private PlaylistDTO playlist;
    private TrackDTO track;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new PlaylistService();
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        sut.setPlaylistDAO(playlistDAO);

        playlistId = 0;
        trackId = 0;
        token = new TokenDTO();
        playlist = new PlaylistDTO();
        track = new TrackDTO();
    }

    @Test
    void testGetAllPlaylistsCallsDAOWithoutException() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.getAllPlaylists(token.getToken());

        // Assert
        Mockito.verify(playlistDAO).getAllPlaylists(token.getToken());
    }

    @Test
    void testGetAllPlaylistsCallsDAOWithException() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(ForbiddenException.class, () -> sut.getAllPlaylists(token.getToken()));
    }

    @Test
    void testAddPlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.addPlaylist(playlist, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).addPlaylist(playlist, token.getToken());
    }

    @Test
    void testAddPlaylistThrowsRequestInvalidException() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.addPlaylist(null, token.getToken()));
    }

    @Test
    void testEditPlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.editPlaylist(playlistId, playlist, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).editPlaylist(playlistId, playlist, token.getToken());
    }

    @Test
    void testEditPlaylistThrowsRequestInvalidException1() {
        // Arrange
        int playlistId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.editPlaylist(playlistId, playlist, token.getToken()));
    }

    @Test
    void testEditPlaylistThrowsRequestInvalidException2() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.editPlaylist(playlistId, null, token.getToken()));
    }

    @Test
    void testDeletePlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.deletePlaylist(playlistId, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).deletePlaylist(playlistId, token.getToken());
    }

    @Test
    void testDeletePlaylistThrowsRequestInvalidException() {
        // Arrange
        int playlistId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.deletePlaylist(playlistId, token.getToken()));
    }

    @Test
    void testGetAllTracksFromPlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.getAllTracksFromPlaylist(playlistId, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).getAllTracksFromPlaylist(playlistId, token.getToken());
    }

    @Test
    void testGetAllTracksFromPlaylistThrowsRequestInvalidException() {
        // Arrange
        int playlistId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.getAllTracksFromPlaylist(playlistId, token.getToken()));
    }

    @Test
    void testAddTrackToPlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.addTrackToPlaylist(playlistId, track, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).addTrackToPlaylist(playlistId, track, token.getToken());
    }

    @Test
    void testAddTrackToPlaylistThrowsRequestInvalidException1() {
        // Arrange
        int playlistId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.addTrackToPlaylist(playlistId, track, token.getToken()));
    }

    @Test
    void testAddTrackToPlaylistThrowsRequestInvalidException2() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.addTrackToPlaylist(playlistId, null, token.getToken()));
    }

    @Test
    void testRemoveTrackFromPlaylistCallsDAO() {
        // Arrange
        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act
        sut.removeTrackFromPlaylist(playlistId, trackId, token.getToken());

        // Assert
        Mockito.verify(playlistDAO).removeTrackFromPlaylist(playlistId, trackId, token.getToken());
    }

    @Test
    void testRemoveTrackFromPlaylistThrowsRequestInvalidException1() {
        // Arrange
        int playlistId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.removeTrackFromPlaylist(playlistId, trackId, token.getToken()));
    }

    @Test
    void testRemoveTrackFromPlaylistThrowsRequestInvalidException2() {
        // Arrange
        int trackId = -1;

        Mockito.when(playlistDAO.checkToken(token.getToken())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RequestInvalidException.class, () -> sut.removeTrackFromPlaylist(playlistId, trackId, token.getToken()));
    }
}