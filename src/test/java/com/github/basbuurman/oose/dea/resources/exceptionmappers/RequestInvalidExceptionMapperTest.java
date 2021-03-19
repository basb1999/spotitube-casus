package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestInvalidExceptionMapperTest {
    RequestInvalidExceptionMapper sut;
    RequestInvalidException exception;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new RequestInvalidExceptionMapper();
        exception = new RequestInvalidException();
    }

    @Test
    void testAuthorizationHasFailedExceptionMapper() {
        // Act
        var execute = sut.toResponse(exception);

        // Assert
        Assertions.assertEquals(execute.getStatus(), 400);
    }
}