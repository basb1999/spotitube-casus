package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.ForbiddenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException e) {
        return Response.status(403).build();
    }
}