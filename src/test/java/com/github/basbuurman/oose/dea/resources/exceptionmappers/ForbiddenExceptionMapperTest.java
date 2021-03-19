package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.ForbiddenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ForbiddenExceptionMapperTest {
    ForbiddenExceptionMapper sut;
    ForbiddenException exception;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new ForbiddenExceptionMapper();
        exception = new ForbiddenException();
    }

    @Test
    void testAuthorizationHasFailedExceptionMapper() {
        // Act
        var execute = sut.toResponse(exception);

        // Assert
        Assertions.assertEquals(execute.getStatus(), 403);
    }
}