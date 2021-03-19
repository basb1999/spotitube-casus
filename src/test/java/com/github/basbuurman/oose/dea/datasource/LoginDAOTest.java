package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.datamappers.TokenMapper;
import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

class LoginDAOTest {
    private LoginDAO sut;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private TokenMapper tokenMapper;

    private String sql;
    private UserDTO user;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new LoginDAO();
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        connection = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        tokenMapper = Mockito.mock(TokenMapper.class);
        sut.setDatabaseConnection(connectionFactory);

        sql = "SELECT * FROM users WHERE user = ? AND password = ?";
        user = new UserDTO();
    }

    @Test
    void testGetUserToken() throws SQLException {
        // Arrange
        Mockito.when(connectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Act
        sut.getUserToken(user);

        // Assert
        Mockito.verify(connection).prepareStatement(sql);
    }
}