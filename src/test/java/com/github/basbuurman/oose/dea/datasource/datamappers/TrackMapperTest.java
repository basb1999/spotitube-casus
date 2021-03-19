package com.github.basbuurman.oose.dea.datasource.datamappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

class TrackMapperTest {
    private TrackMapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new TrackMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    void testTrackMapperSucces() throws SQLException {
        // Act
        sut.insert(resultSet);

        // Assert
        Mockito.verify(resultSet).getInt("trackId");
        Mockito.verify(resultSet).getString("title");
        Mockito.verify(resultSet).getString("performer");
        Mockito.verify(resultSet).getInt("duration");
        Mockito.verify(resultSet).getString("album");
        Mockito.verify(resultSet).getInt("playcount");
        Mockito.verify(resultSet).getString("publicationDate");
        Mockito.verify(resultSet).getString("description");
        Mockito.verify(resultSet).getBoolean("offlineAvailable");
    }

    @Test
    void testTrackMapperFailure() throws SQLException {
        // Arrange
        Mockito.when(resultSet.getString(Mockito.anyString())).thenThrow(SQLException.class);

        // Act & Assert
        Assertions.assertThrows(SQLException.class, () -> sut.insert(resultSet));
    }
}