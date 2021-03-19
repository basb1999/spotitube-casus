package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.TrackDAO;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class TrackServiceTest {
    private TrackService sut;
    private TrackDAO trackDAO;

    private int playlistId;
    private TokenDTO token;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new TrackService();
        trackDAO = Mockito.mock(TrackDAO.class);
        sut.setTrackDAO(trackDAO);

        playlistId = 0;
        token = new TokenDTO();
    }

    @Test
    void testGetAllTracksCallsDAO() {
        // Arrange
        List<TrackDTO> tracks = new ArrayList<>();

        Mockito.when(trackDAO.getAllTracks(playlistId, token.getToken())).thenReturn(tracks);

        // Act
        sut.getAllTracks(playlistId, token.getToken());

        // Assert
        Mockito.verify(trackDAO).getAllTracks(playlistId, token.getToken());
    }
}