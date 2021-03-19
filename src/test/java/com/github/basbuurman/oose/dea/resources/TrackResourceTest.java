package com.github.basbuurman.oose.dea.resources;

import com.github.basbuurman.oose.dea.datasource.TrackDAO;
import com.github.basbuurman.oose.dea.services.TrackService;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TrackResourceTest {
    private TrackResource sut;
    private TrackService trackService;
    private TrackDAO trackDAO;

    private int playlistId;
    private TokenDTO token;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new TrackResource();
        trackService = Mockito.mock(TrackService.class);
        trackDAO = Mockito.mock(TrackDAO.class);
        trackService.setTrackDAO(trackDAO);
        sut.setTrackService(trackService);

        playlistId = 1;
        token = new TokenDTO();
    }

    @Test
    void testIfGetAllAvailableTracksCallsGetAllAvailableTracksOnService() {
        // Act
        sut.getAllAvailableTracks(playlistId, token.getToken());

        // Assert
        Mockito.verify(trackService).getAllTracks(playlistId, token.getToken());
    }

    @Test
    void testIfGetAllAvailableTracksReturns200() {
        // Act
        var execute = sut.getAllAvailableTracks(playlistId, token.getToken());

        // Assert
        Assertions.assertEquals(200, execute.getStatus());
    }
}