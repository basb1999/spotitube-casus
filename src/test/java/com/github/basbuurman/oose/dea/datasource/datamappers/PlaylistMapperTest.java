package com.github.basbuurman.oose.dea.datasource.datamappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

class PlaylistMapperTest {
    private PlaylistMapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new PlaylistMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    void testPlaylistMapperSucces() throws SQLException {
        // Act
        sut.insert(resultSet);

        // Assert
        Mockito.verify(resultSet).getInt("playlistId");
        Mockito.verify(resultSet).getString("name");
        Mockito.verify(resultSet).getBoolean("owner");
    }

    @Test
    void testPlaylistMapperFailure() throws SQLException {
        // Arrange
        Mockito.when(resultSet.getString(Mockito.anyString())).thenThrow(SQLException.class);

        // Act & Assert
        Assertions.assertThrows(SQLException.class, () -> sut.insert(resultSet));
    }
}