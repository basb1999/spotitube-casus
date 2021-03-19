package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.LoginDAO;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;
import com.github.basbuurman.oose.dea.services.exceptions.AuthorizationHasFailedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class LoginServiceTest {
    private LoginService sut;
    private LoginDAO loginDAO;

    private UserDTO user;
    private TokenDTO token;
    List<TokenDTO> tokens;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new LoginService();
        loginDAO = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(loginDAO);

        user = new UserDTO();
        token = new TokenDTO();
        tokens = new ArrayList<>();
    }

    @Test
    void testLoginCallsLoginDAO() {
        // Arrange
        tokens.add(token);

        Mockito.when(loginDAO.getUserToken(user)).thenReturn(tokens);

        // Act
        sut.login(user);

        // Assert
        Mockito.verify(loginDAO).getUserToken(user);
    }

    @Test
    void testLoginThrowsAuthorizationHasFailedException() {
        // Arrange
        Mockito.when(loginDAO.getUserToken(user)).thenReturn(tokens);

        // Act & Assert
        Assertions.assertThrows(AuthorizationHasFailedException.class, () -> sut.login(user));
    }
}