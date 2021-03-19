package com.github.basbuurman.oose.dea.resources;

import com.github.basbuurman.oose.dea.datasource.LoginDAO;
import com.github.basbuurman.oose.dea.services.LoginService;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;
import com.github.basbuurman.oose.dea.services.exceptions.AuthorizationHasFailedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoginResourceTest {
    private LoginResource sut;
    private LoginService loginService;
    private LoginDAO loginDAO;

    private UserDTO user;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new LoginResource();
        loginService = Mockito.mock(LoginService.class);
        loginDAO = Mockito.mock(LoginDAO.class);
        loginService.setLoginDAO(loginDAO);
        sut.setLoginService(loginService);

        user = new UserDTO();
    }

    @Test
    void testIfLoginCallsCheckLoginOnService() {
        // Act
        sut.login(user);

        // Assert
        Mockito.verify(loginService).login(user);
    }

    @Test
    void testIfLoginThrowsExceptionFromServicePassesThrough() {
        // Act & Assert
        Mockito.doThrow(AuthorizationHasFailedException.class).when(loginService).login(user);
    }

    @Test
    void testIfLoginReturns200() {
        // Act
        var login = sut.login(user);

        // Assert
        Assertions.assertEquals(200, login.getStatus());
    }
}