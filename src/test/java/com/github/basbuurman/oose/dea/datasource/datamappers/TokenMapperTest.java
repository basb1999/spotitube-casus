package com.github.basbuurman.oose.dea.datasource.datamappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

class TokenMapperTest {
    private TokenMapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new TokenMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    void testTokenMapperSucces() throws SQLException {
        // Act
        sut.insert(resultSet);

        // Assert
        Mockito.verify(resultSet).getString("token");
        Mockito.verify(resultSet).getString("name");
    }

    @Test
    void testTokenMapperFailure() throws SQLException {
        // Arrange
        Mockito.when(resultSet.getString(Mockito.anyString())).thenThrow(SQLException.class);

        // Act & Assert
        Assertions.assertThrows(SQLException.class, () -> sut.insert(resultSet));
    }
}