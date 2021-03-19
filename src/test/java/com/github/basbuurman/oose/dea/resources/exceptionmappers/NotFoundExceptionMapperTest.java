package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotFoundExceptionMapperTest {
    NotFoundExceptionMapper sut;
    NotFoundException exception;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new NotFoundExceptionMapper();
        exception = new NotFoundException();
    }

    @Test
    void testAuthorizationHasFailedExceptionMapper() {
        // Act
        var execute = sut.toResponse(exception);

        // Assert
        Assertions.assertEquals(execute.getStatus(), 404);
    }
}