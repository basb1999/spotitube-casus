package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.AuthorizationHasFailedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorizationHasFailedExceptionMapperTest {
    AuthorizationHasFailedExceptionMapper sut;
    AuthorizationHasFailedException exception;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new AuthorizationHasFailedExceptionMapper();
        exception = new AuthorizationHasFailedException();
    }

    @Test
    void testAuthorizationHasFailedExceptionMapper() {
        // Act
        var execute = sut.toResponse(exception);

        // Assert
        Assertions.assertEquals(execute.getStatus(), 401);
    }
}