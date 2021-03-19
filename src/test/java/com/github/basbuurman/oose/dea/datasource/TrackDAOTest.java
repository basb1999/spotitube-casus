package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class TrackDAOTest {
    private TrackDAO sut;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new TrackDAO();
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        connection = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        sut.setDatabaseConnection(connectionFactory);
    }

    @Test
    void testGetAllTracks() throws SQLException {
        // Arrange
        String sql = "SELECT * FROM tracks WHERE NOT EXISTS(SELECT * FROM playlistTrack pT WHERE tracks.trackId = pT.trackId AND pT.playlistId = ?)";
        int playlistId = 1;
        String token = "";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.getAllTracks(playlistId, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }
}