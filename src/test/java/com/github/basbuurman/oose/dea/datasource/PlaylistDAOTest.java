package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class PlaylistDAOTest {
    private PlaylistDAO sut;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private int playlistId;
    private int trackId;
    private String token;
    private PlaylistDTO playlist;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new PlaylistDAO();
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        connection = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        sut.setDatabaseConnection(connectionFactory);

        playlistId = 0;
        trackId = 0;
        token = "";
        playlist = new PlaylistDTO();
    }

    @Test
    void testGetAllPlaylists() throws SQLException {
        // Arrange
        String sql = "SELECT * FROM playlists";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.getAllPlaylists(token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }

    @Test
    void testAddPlaylist() throws SQLException {
        // Arrange
        String sql1 = "INSERT INTO playlists (name, owner) VALUES (?, true)";
        String sql2 = "SELECT user FROM users WHERE token = ?";
        String sql3 = "SELECT playlistId FROM playlists WHERE name = ?";
        String sql4 = "INSERT INTO playlistUser VALUES (?, ?)";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql2)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql4)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.addPlaylist(playlist, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql1);
        Mockito.verify(connection).prepareStatement(sql2);
        Mockito.verify(connection).prepareStatement(sql3);
        Mockito.verify(connection).prepareStatement(sql4);
    }

    @Test
    void testEditPlaylist() throws SQLException {
        // Arrange
        String sql = "UPDATE playlists SET name = ? WHERE playlistId = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Act
        sut.editPlaylist(playlistId, playlist, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }

    @Test
    void testDeletePlaylist() throws SQLException {
        // Arrange
        String sql1 = "DELETE FROM playlistUser WHERE playlistId = ?";
        String sql2 = "DELETE FROM playlistTrack WHERE playlistId = ?";
        String sql3 = "DELETE FROM playlists WHERE playlistId = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql2)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql3)).thenReturn(preparedStatement);

        // Act
        sut.deletePlaylist(playlistId, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql1);
        Mockito.verify(connection).prepareStatement(sql2);
        Mockito.verify(connection).prepareStatement(sql3);
    }

    @Test
    void testGetAllTracksFromPlaylist() throws SQLException {
        // Arrange
        String sql = "SELECT t.trackId AS 'trackId', title, performer, duration, album, playcount, publicationDate, description, offlineAvailable FROM playlists p INNER JOIN playlistTrack pT on p.playlistId = pT.playlistId INNER JOIN tracks t on pT.trackId = t.trackId WHERE p.playlistId = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.getAllTracksFromPlaylist(playlistId, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }

    @Test
    void testAddTrackToPlaylist() throws SQLException {
        // Arrange
        String sql1 = "INSERT INTO playlistTrack VALUES (?, ?)";
        String sql2 = "UPDATE tracks SET offlineAvailable = ? WHERE trackId = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql1)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(sql2)).thenReturn(preparedStatement);

        // Act
        sut.addTrackToPlaylist(playlistId, new TrackDTO(), token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql1);
        Mockito.verify(connection).prepareStatement(sql2);
    }

    @Test
    void testRemoveTrackFromPlaylist() throws SQLException {
        // Arrange
        String sql = "DELETE FROM playlistTrack WHERE playlistId = ? AND trackId = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        // Act
        sut.removeTrackFromPlaylist(playlistId, trackId, token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }

    @Test
    void testCalculateTotalDurationPlaylists() throws SQLException {
        // Arrange
        String sql = "SELECT SUM(t.duration) AS 'totalDurationPlaylists' FROM tracks t INNER JOIN playlistTrack pT on t.trackId = pT.trackId";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.calculateTotalDurationPlaylists();

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }

    @Test
    void testCheckToken() throws SQLException {
        // Arrange
        String sql = "SELECT * FROM users WHERE token = ?";

        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.checkToken(token);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }
}